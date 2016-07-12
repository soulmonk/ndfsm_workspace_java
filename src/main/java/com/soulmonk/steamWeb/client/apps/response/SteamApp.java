package com.soulmonk.steamWeb.client.apps.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 10:58 AM
 */
public class SteamApp {

    private String appId;
    private String name;

    @JsonProperty("appid")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
