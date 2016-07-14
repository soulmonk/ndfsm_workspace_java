package com.soulmonk.steamWeb.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soulmonk.steamWeb.client.base.SteamRequest;
import com.soulmonk.steamWeb.client.base.WrapJsonRootElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Properties;

public class SteamApi {

    protected String steamKey;
    protected static String apiUrl = "api.steampowered.com";
    protected static String apiProtocol = "http";
    protected HttpClient client;
    protected HttpEntity entity;

    public SteamApi() {
        Resource resource = new ClassPathResource("/steamweb.properties");
        try {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            steamKey = props.getProperty("steamKey");
            client = HttpClients.createDefault();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SteamApi(String steamKey) {
        this.steamKey = steamKey;
        client = HttpClients.createDefault();
    }

    public SteamApi(String steamKey, HttpClient client) {
        this.steamKey = steamKey;
        this.client = client;
    }


    public <T> Object execute(SteamRequest request) {
        // HttpGet getRequest = new
        // HttpGet("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key="
        // + steamKey + "&steamids=" + steamId + "&format=json");
        Object responseObject = null;
        HttpGet getRequest = null;
        Class responseType = request.getResponseType();
        String response = null;
        try {

            URIBuilder builder = new URIBuilder();
            builder.setScheme(apiProtocol).setHost(apiUrl);
            builder.setPath(request.getSteamRoute() + request.getSteamMethod()
                    + request.getSteamMethodVersion());
            builder.setParameter("key", this.steamKey);

            List<NameValuePair> stemParameters = request.getSteamParameters();

            if (stemParameters != null) {
                for (NameValuePair nvp : stemParameters) {
                    builder.setParameter(nvp.getName(), nvp.getValue());

                }
            }

            builder.setParameter("format", "json");
            URI uri = builder.build();

//			System.out.println(uri.toASCIIString());
            getRequest = new HttpGet(uri);

            entity = client.execute(getRequest).getEntity();

            //check if entity is null
            if (entity == null) {
                System.out.println("Entity null");
            } else {
                response = EntityUtils.toString(entity);
//			System.out.println("URL" + uri.toString() + "response: " + response);
                ObjectMapper mapper = new ObjectMapper();
                if (WrapJsonRootElement.class.isAssignableFrom(responseType)) {
                    mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
                }
                responseObject = mapper.readValue(response, responseType);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(entity);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (getRequest != null) {
                getRequest.reset();
            }
        }

        //System.out.println(response);
        return responseObject;
    }

    public String getSteamKey() {
        return steamKey;
    }

    public void setSteamKey(String steamKey) {
        this.steamKey = steamKey;
    }

    public void close() {
        try {
            ((CloseableHttpClient) client).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
