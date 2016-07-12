package com.soulmonk.steamWeb.client.dota.response;

import com.soulmonk.steamWeb.shared.MatchHistoryBySequence;

public class MatchHistoryBySequenceResponse {
    private MatchHistoryBySequence result;


    public MatchHistoryBySequenceResponse() {

    }

    //Setter
    public void setResult(MatchHistoryBySequence result) {
        this.result = result;
    }

    //Getter
    public MatchHistoryBySequence getResult() {
        return result;
    }
}
