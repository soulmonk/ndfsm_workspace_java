package com.soulmonk.steamWeb.client.dota.economy;

import com.soulmonk.steamWeb.client.dota.economy.response.GetGameItemsResponse;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 7:15 PM
 */
public class GetGameItemsRequest extends EconomyDotaRequest {

    private static final String STEAM_METHOD = "/GetGameItems";
    private static final String STEAM_METHOD_VERSION = "/V1";
    private static final Class RESPONSE_TYPE = GetGameItemsResponse.class;

    public GetGameItemsRequest() {
        super();
        setSteamMethod(STEAM_METHOD);
        setSteamMethodVersion(STEAM_METHOD_VERSION);
        setResponseType(RESPONSE_TYPE);
        setLanguage("en");
    }

    public void setLanguage(String language) {
        parameters.put("language", language);
    }
}
