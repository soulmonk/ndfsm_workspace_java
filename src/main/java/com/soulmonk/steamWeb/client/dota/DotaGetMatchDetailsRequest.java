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

    public DotaGetMatchDetailsRequest() {
        super();
        setSteamMethod(STEAM_METHOD);
        setSteamMethodVersion(STEAM_METHOD_VERSION);
        setResponseType(RESPONSE_TYPE);
    }

    public DotaGetMatchDetailsRequest(String match_id) {
        this();
        setMatchId(match_id);
    }

    public void setMatchId(String matchId) {
        parameters.put("match_id", matchId);
    }
}
