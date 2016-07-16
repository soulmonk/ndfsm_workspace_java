package com.soulmonk.steamWeb.client.dota;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.dota.response.MatchDetailResponse;
import com.soulmonk.steamWeb.client.dota.response.MatchHistoryBySequenceResponse;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotaGetMatchHistoryBySequenceRequest extends DotaRequest {
    private static final String STEAM_METHOD = "/GetMatchHistoryBySequenceNum";
    private static final String STEAM_METHOD_VERSION = "/v1";
    private static final Class RESPONSE_TYPE = MatchHistoryBySequenceResponse.class;
    private static final Integer DEFAULT_NUM_MATCHES = 100;

    public DotaGetMatchHistoryBySequenceRequest() {
        super();
        setSteamMethod(STEAM_METHOD);
        setSteamMethodVersion(STEAM_METHOD_VERSION);
        setResponseType(RESPONSE_TYPE);

        setMatchRequestNumber(DEFAULT_NUM_MATCHES);
    }

    public void setMatchRequestNumber(int matchRequestNumber) {
        parameters.put("matches_requested", String.valueOf(matchRequestNumber));
    }

    public void setSequenceNumber(String sequenceNumber) {
        parameters.put("start_at_match_seq_num", sequenceNumber);
    }
}
