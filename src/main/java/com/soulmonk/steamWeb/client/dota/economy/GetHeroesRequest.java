package com.soulmonk.steamWeb.client.dota.economy;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.dota.economy.response.GetHeroesResponse;
import com.soulmonk.steamWeb.shared.HeroesList;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 4:27 PM
 */
public class GetHeroesRequest extends EconomyDotaRequest {

    private static final String STEAM_METHOD = "/GetHeroes";
    private static final String STEAM_METHOD_VERSION = "/V1";
    private static final Class RESPONSE_TYPE = GetHeroesResponse.class;

    private Map<String, String> parameters;

    public GetHeroesRequest() {
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

    public void setItemizedonly(Boolean itemizedonly) {
        parameters.put("language", itemizedonly.toString());
    }

    @Override
    public Class getResponseType() {
        return RESPONSE_TYPE;
    }
}
