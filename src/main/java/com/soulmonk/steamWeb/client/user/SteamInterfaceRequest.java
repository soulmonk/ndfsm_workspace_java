package com.soulmonk.steamWeb.client.user;

import com.soulmonk.steamWeb.client.base.SteamRequest;

public abstract class SteamInterfaceRequest implements SteamRequest {

    private static final String STEAM_ROUTE = "/ISteamUser";

    @Override
    public String getSteamRoute() {
        return STEAM_ROUTE;
    }
}
