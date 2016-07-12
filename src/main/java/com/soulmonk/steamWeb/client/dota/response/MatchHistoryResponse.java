package com.soulmonk.steamWeb.client.dota.response;

import com.soulmonk.steamWeb.shared.MatchHistoryResults;

public class MatchHistoryResponse {
    private MatchHistoryResults result;


    public MatchHistoryResponse() {

    }

    //Setter
    public void setResult(MatchHistoryResults result) {
        this.result = result;
    }

    //Getter
    public MatchHistoryResults getResult() {
        return result;
    }
}
