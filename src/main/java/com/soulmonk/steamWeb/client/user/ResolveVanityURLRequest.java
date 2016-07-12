package com.soulmonk.steamWeb.client.user;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.user.response.ResolveVanityURLResponse;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 12:35 PM
 */
public class ResolveVanityURLRequest extends SteamInterfaceRequest {

    private static final String STEAM_METHOD = "/ResolveVanityURL";
    private static final String STEAM_METHOD_VERSION = "/V001";
    private static final Class RESPONSE_TYPE = ResolveVanityURLResponse.class;

    private Map<String, String> parameters;

    public ResolveVanityURLRequest() {
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
    public List<NameValuePair> getSteamParameters() {
        return UriUtils.stringMapToNameValuePairs(parameters);
    }

    @Override
    public Class getResponseType() {
        return RESPONSE_TYPE;
    }

    public void setVanityurl(String vanityurl) {
        parameters.put("vanityurl", vanityurl);
    }
}
