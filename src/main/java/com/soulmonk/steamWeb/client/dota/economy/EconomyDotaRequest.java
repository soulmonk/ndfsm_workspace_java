package com.soulmonk.steamWeb.client.dota.economy;

import com.soulmonk.steamWeb.client.base.AbstractSteamRequest;
import com.soulmonk.steamWeb.client.base.SteamRequest;

public abstract class EconomyDotaRequest extends AbstractSteamRequest {

    private static final String STEAM_ROUTE = "/IEconDOTA2_570";

    public EconomyDotaRequest() {
        super();
        setSteamRoute(STEAM_ROUTE);
    }
}
