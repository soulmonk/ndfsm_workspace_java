package com.soulmonk.steamWeb.client.dota.economy;

import com.soulmonk.steamWeb.client.dota.economy.response.GetHeroesResponse;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 4:27 PM
 */
public class GetHeroesRequest extends EconomyDotaRequest {

    private static final String STEAM_METHOD = "/GetHeroes";
    private static final String STEAM_METHOD_VERSION = "/V1";
    private static final Class RESPONSE_TYPE = GetHeroesResponse.class;

    public GetHeroesRequest() {
        super();
        setSteamMethod(STEAM_METHOD);
        setSteamMethodVersion(STEAM_METHOD_VERSION);
        setResponseType(RESPONSE_TYPE);
        setLanguage("en");
    }

    public void setLanguage(String language) {
        parameters.put("language", language);
    }

    public void setItemizedonly(Boolean itemizedonly) {
        parameters.put("language", itemizedonly.toString());
    }
}
