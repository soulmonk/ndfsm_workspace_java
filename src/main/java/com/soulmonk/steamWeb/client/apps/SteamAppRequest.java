package com.soulmonk.steamWeb.client.apps;

import com.soulmonk.steamWeb.client.base.SteamRequest;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 10:34 AM
 */
public abstract class SteamAppRequest implements SteamRequest {

    private static final String STEAM_ROUTE = "/ISteamApps";

    @Override
    public String getSteamRoute() {
        return STEAM_ROUTE;
    }
}
