package com.soulmonk.steamWeb.client.apps;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.apps.response.SteamAppListResponse;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 10:32 AM
 */
public class GetSteamAppListRequest extends SteamAppRequest {

    private static final String STEAM_METHOD = "/GetAppList";
    private static final String STEAM_METHOD_VERSION = "/v2";
    private static final Class RESPONSE_TYPE = SteamAppListResponse.class;


    private Map<String, String> parameters;

    public GetSteamAppListRequest() {
        parameters = new HashMap<String, String>();
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
    public Class getResponseType() {
        return RESPONSE_TYPE;
    }

    @Override
    public List<NameValuePair> getSteamParameters() {
        return UriUtils.stringMapToNameValuePairs(parameters);
    }
}
