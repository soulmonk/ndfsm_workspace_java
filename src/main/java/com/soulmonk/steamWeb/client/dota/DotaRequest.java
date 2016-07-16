package com.soulmonk.steamWeb.client.dota;

import com.soulmonk.steamWeb.client.base.AbstractSteamRequest;
import com.soulmonk.steamWeb.client.base.SteamRequest;

public abstract class DotaRequest extends AbstractSteamRequest {

    private static final String STEAM_ROUTE = "/IDOTA2Match_570";

    public DotaRequest() {
        super();
        setSteamRoute(STEAM_ROUTE);
    }
}
