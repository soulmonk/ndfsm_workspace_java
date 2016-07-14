package com.soulmonk.steamWeb.client.dota.economy;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.dota.economy.response.GetGameItemsResponse;
import com.soulmonk.steamWeb.shared.HeroesList;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 7:15 PM
 */
public class GetGameItemsRequest extends EconomyDotaRequest {

    private static final String STEAM_METHOD = "/GetGameItems";
    private static final String STEAM_METHOD_VERSION = "/V1";
    private static final Class RESPONSE_TYPE = GetGameItemsResponse.class;

    private Map<String, String> parameters;

    public GetGameItemsRequest() {
        parameters = new HashMap<String, String>();
        setLanguage("en");
    }

    @Override
    public String getSteamMethod() {
        return STEAM_METHOD;
    }

    @Override
    public String getSteamMethodVersion() {
        return STEAM_METHOD_VERSION;
    }

    @Override
    public List<NameValuePair> getSteamParameters() {
        return UriUtils.stringMapToNameValuePairs(parameters);
    }

    public void setLanguage(String language) {
        parameters.put("language", language);
    }

    @Override
    public Class getResponseType() {
        return RESPONSE_TYPE;
    }
}
