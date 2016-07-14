package com.soulmonk.steamWeb.client.user;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.user.response.SteamPlayerSummary;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SteamGetPlayerSummaryRequest extends SteamInterfaceRequest {

    private static final String STEAM_METHOD = "/GetPlayerSummaries";
    private static final String STEAM_METHOD_VERSION = "/V002";
    private static final Class RESPONSE_TYPE = SteamPlayerSummary.class;

    private Map<String, String> parameters;
    //Todo add List of the steam ids

    public SteamGetPlayerSummaryRequest() {
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

    public void setSteamId(String steamId) {
        parameters.put("steamids", steamId);
    }

    public void addSteamId(String steamId) {
        String steamIds = parameters.get("steamids");
        steamId = steamIds == null ? steamId : steamIds + "," + steamId;
        setSteamId(steamId);
    }

    public void setSteamIds(Collection<String> steamIds) {
        setSteamId(StringUtils.join(steamIds, ","));
    }

    public void setSteamParameters(Map<String, String> steamParameters) {
        this.parameters = steamParameters;
    }

    @Override
    public Class getResponseType() {
        return RESPONSE_TYPE;
    }
}