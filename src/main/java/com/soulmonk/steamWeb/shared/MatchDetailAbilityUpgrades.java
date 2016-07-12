package com.soulmonk.steamWeb.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetailAbilityUpgrades {
    private int ability;
    private int time;
    private int level;
    private int id;


    //Constructor
    public MatchDetailAbilityUpgrades() {

    }

    //Getter
    public int getId() {
        return id;
    }

    public int getAbility() {
        return ability;
    }

    public int getTime() {
        return time;
    }

    public int getLevel() {
        return level;
    }

    //Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}

