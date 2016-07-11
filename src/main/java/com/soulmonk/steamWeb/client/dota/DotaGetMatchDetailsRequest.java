package com.soulmonk.steamWeb.client.dota;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.dota.response.MatchDetailResponse;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotaGetMatchDetailsRequest extends DotaRequest {

	private static final String STEAM_METHOD = "/GetMatchDetails";
	private static final String STEAM_METHOD_VERSION = "/V001";
	private static final Class RESPONSE_TYPE = MatchDetailResponse.class;
	private Map<String, String> parameters;

	public DotaGetMatchDetailsRequest() {
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

	public void setMatchId(String matchId) {
		parameters.put("match_id", matchId);
	}
	@Override
	public Class<MatchDetailResponse> getResponseType(){
		return RESPONSE_TYPE;
	}
	
}
