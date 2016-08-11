package com.soulmonk.ndfsm.configuration;

import com.soulmonk.ndfsm.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Created by SoulMonk on 11.08.2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@ComponentScan(basePackageClasses = UserDetailsImpl.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                    .antMatchers("/dev/**", "/test/**").hasRole("DEV")
                    .antMatchers("/dev/**", "/about").hasRole("DEV")
                    .antMatchers("/admin/**").hasRole("DEV")
                    .antMatchers("/resources/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()  // #8
                    .loginPage("/login") // #9
                    .permitAll() // #5
                    .defaultSuccessUrl("/")
                /*.failureUrl("/login?failure=true")*/
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")
                .and()
                    .rememberMe()
                    .rememberMeServices(rememberMeServices())
                .and()
                .csrf()
                    .disable()
        ;
    }

    @Bean(name = "rememberMeService")
    protected RememberMeServices rememberMeServices() {
        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices("myAppKey", userDetailsService(), tokenRepository());
        rememberMeServices.setCookieName("remember-me");
        rememberMeServices.setTokenLength(32);
        rememberMeServices.setParameter("remember_me_parameter");
        return null;
    }

    // установка провайдера авторизации (может быть примитивная - InMemory, или на основе токенов, связанная с БД и т.д.
    // в данном случае это установка кастомного провайдера
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean(name = "userDetailsService")
    protected UserDetailsService userDetailsService() {
        return new UserDetailsImpl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // кодер для паролей; на смену deprecated org.springframework.security.authentication.encoding.PasswordEncoder
    // относительно недавно появился новый интерфейс org.springframework.security.crypto.password.PasswordEncoder
    // можно использовать BCryptPasswordEncoder на основе хеш-функции BCrypt, или StandartPasswordEncoder, базирующийся
    // на алгоритме SHA-256 или NoOpPasswordEncoder без шифрования пароля (рекомендован для фазы разработки)
    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "tokenRepository")
    protected PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }
}
