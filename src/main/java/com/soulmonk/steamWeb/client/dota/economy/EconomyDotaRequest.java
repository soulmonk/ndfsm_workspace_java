package com.soulmonk.steamWeb.client.dota.economy;

import com.soulmonk.steamWeb.client.base.SteamRequest;

public abstract class EconomyDotaRequest implements SteamRequest {

    private static final String STEAM_ROUTE = "/IEconDOTA2_570";

    @Override
    public String getSteamRoute() {
        return STEAM_ROUTE;
    }
}
