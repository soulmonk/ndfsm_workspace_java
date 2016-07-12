package com.soulmonk.steamWeb.client.dota.response;

import com.soulmonk.steamWeb.shared.MatchDetail;


public class MatchDetailResponse implements java.io.Serializable {

    private MatchDetail result;


    //constructor
    public MatchDetailResponse() {

    }


    //setter
    public void setResult(MatchDetail result) {
        this.result = result;
    }


    //getter
    public MatchDetail getResult() {
        return result;
    }
}
