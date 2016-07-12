package com.soulmonk.steamWeb.client.apps.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 10:39 AM
 */
public class SteamAppListResponse {

    private SteamAppList steamAppList;

    @JsonProperty("applist")
    public SteamAppList getSteamAppList() {
        return steamAppList;
    }

    @JsonProperty("applist")
    public void setSteamAppList(SteamAppList steamAppList) {
        this.steamAppList = steamAppList;
    }
}
