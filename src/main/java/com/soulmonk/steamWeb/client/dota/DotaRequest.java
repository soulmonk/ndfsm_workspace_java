package com.soulmonk.steamWeb.client.dota;

import com.soulmonk.steamWeb.client.base.SteamRequest;

public abstract class DotaRequest implements SteamRequest {

    private static final String STEAM_ROUTE = "/IDOTA2Match_570";

    @Override
    public String getSteamRoute() {
        return STEAM_ROUTE;
    }
}
