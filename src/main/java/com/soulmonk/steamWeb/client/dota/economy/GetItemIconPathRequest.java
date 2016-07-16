package com.soulmonk.steamWeb.client.dota.economy;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.dota.economy.response.GetItemIconPathResponse;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 8:35 PM
 */
public class GetItemIconPathRequest extends EconomyDotaRequest {


    private static final String STEAM_METHOD = "/GetItemIconPath";
    private static final String STEAM_METHOD_VERSION = "/V1";
    private static final Class RESPONSE_TYPE = GetItemIconPathResponse.class;

    public GetItemIconPathRequest() {
        super();
        setSteamMethod(STEAM_METHOD);
        setSteamMethodVersion(STEAM_METHOD_VERSION);
        setResponseType(RESPONSE_TYPE);
    }

    public void setIconname(String language) {
        parameters.put("iconname", language);
    }

    public void setIcontype(String language) {
        parameters.put("icontype", language);
    }
}
