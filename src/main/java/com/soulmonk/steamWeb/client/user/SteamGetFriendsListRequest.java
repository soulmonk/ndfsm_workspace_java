package com.soulmonk.steamWeb.client.user;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.user.response.SteamFriendsListResponse;
import com.soulmonk.steamWeb.client.user.response.SteamPlayerSummary;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SteamGetFriendsListRequest extends SteamInterfaceRequest{
	private static final String STEAM_METHOD = "/GetFriendList";
	private static final String STEAM_METHOD_VERSION = "/v0001";
	private static final Class RESPONSE_TYPE = SteamFriendsListResponse.class;

	private Map<String, String> parameters;
	
	public SteamGetFriendsListRequest() {
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
		parameters.put("steamid", steamId);
	}
	
	public void setSteamParameters(Map<String,String> steamParameters){
		this.parameters = steamParameters;
	}
	@Override
	public Class<SteamPlayerSummary> getResponseType(){
		return RESPONSE_TYPE;
	}


}
