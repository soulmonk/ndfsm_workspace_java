package com.soulmonk.steamWeb.client.dota.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.soulmonk.steamWeb.shared.MatchHistoryResults;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchHistoryResponse {
    private MatchHistoryResults result;


    public MatchHistoryResponse() {

    }

    //Setter
    @JsonProperty("result")
    public void setResult(MatchHistoryResults result) {
        this.result = result;
    }

    //Getter
    @JsonProperty("result")
    public MatchHistoryResults getResult() {
        return result;
    }
}
