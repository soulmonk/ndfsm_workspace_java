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
    protected static String STEAM_METHOD = "/GetAppList";
    protected static String STEAM_METHOD_VERSION = "/v2";
    protected static Class RESPONSE_TYPE = SteamAppListResponse.class;

    public GetSteamAppListRequest() {
        super();
        setSteamMethod(STEAM_METHOD);
        setSteamMethodVersion(STEAM_METHOD_VERSION);
        setResponseType(RESPONSE_TYPE);
    }
}
