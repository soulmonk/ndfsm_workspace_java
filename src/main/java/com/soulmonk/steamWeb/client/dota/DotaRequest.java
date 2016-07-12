package com.soulmonk.steamWeb.client.dota;

import com.soulmonk.steamWeb.client.user.SteamRequest;

public abstract class DotaRequest implements SteamRequest {

    private static final String STEAM_ROUTE = "/IDOTA2Match_570";

    @Override
    public String getSteamRoute() {
        return STEAM_ROUTE;
    }


    public String getAccountIdFromSteamId(String steamId) {
        String accountId = null;
        try {
            accountId = (Long.parseLong(steamId) - 76561197960265728L)
                    + "";
        } catch (NumberFormatException e) {
            // TODO
        }
        return accountId;
    }

}
