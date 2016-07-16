package com.soulmonk.steamWeb.client.user;

import com.soulmonk.steamWeb.client.base.AbstractSteamRequest;

public abstract class SteamInterfaceRequest extends AbstractSteamRequest {

    private static final String STEAM_ROUTE = "/ISteamUser";

    public SteamInterfaceRequest() {
        super();
        setSteamRoute(STEAM_ROUTE);
    }
}
