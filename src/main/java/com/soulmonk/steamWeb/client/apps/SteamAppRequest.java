package com.soulmonk.steamWeb.client.apps;

import com.soulmonk.steamWeb.client.base.AbstractSteamRequest;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 10:34 AM
 */
public abstract class SteamAppRequest extends AbstractSteamRequest {
    private final static String STEAM_ROUTE = "/ISteamApps";

    public SteamAppRequest() {
        super();
        setSteamRoute(STEAM_ROUTE);
    }
}
