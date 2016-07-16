package com.soulmonk.steamWeb.client.base;

import com.soulmonk.steamWeb.client.UriUtils;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: SoulMonk
 * Date: 7/16/16
 * Time: 1:06 PM
 */
public abstract class AbstractSteamRequest implements SteamRequest {
    private String steamRoute;
    private String steamMethod;
    private String steamMethodVersion;
    protected Class responseType;

    protected Map<String, String> parameters;

    public AbstractSteamRequest() {
        parameters = new HashMap<String, String>();
    }

    public void setParameter(String name, String value) {
        parameters.put(name, value);
    }

    @Override
    public String getSteamRoute() {
        return steamRoute;
    }

    @Override
    public String getSteamMethod() {
        return steamMethod;
    }

    @Override
    public String getSteamMethodVersion() {
        return steamMethodVersion;
    }

    @Override
    public List<NameValuePair> getSteamParameters() {
        return UriUtils.stringMapToNameValuePairs(parameters);
    }

    @Override
    public Class getResponseType() {
        return responseType;
    }

    protected void setSteamRoute(String steamRoute) {
        this.steamRoute = steamRoute;
    }

    protected void setSteamMethod(String steamMethod) {
        this.steamMethod = steamMethod;
    }

    protected void setSteamMethodVersion(String steamMethodVersion) {
        this.steamMethodVersion = steamMethodVersion;
    }

    protected void setResponseType(Class responseType) {
        this.responseType = responseType;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
