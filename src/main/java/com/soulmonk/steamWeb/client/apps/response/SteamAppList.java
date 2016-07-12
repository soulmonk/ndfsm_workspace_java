package com.soulmonk.steamWeb.client.apps.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 10:57 AM
 */
public class SteamAppList {

    private List<SteamApp> steamApps;

    @JsonProperty("apps")
    public List<SteamApp> getSteamApps() {
        return steamApps;
    }

    @JsonProperty("apps")
    public void setSteamApps(List<SteamApp> steamApps) {
        this.steamApps = steamApps;
    }
}
