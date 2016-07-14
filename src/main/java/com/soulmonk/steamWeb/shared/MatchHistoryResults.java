package com.soulmonk.steamWeb.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchHistoryResults {
    private int status;
    private String statusDetail;
    private int numResults;
    private int totalResults;
    private int resultsRemaining;
    private List<MatchHistory> matches;

    //constructor
    MatchHistoryResults() {
    }


    //setter

    @JsonProperty("status")
    public void setStatus(int status) {
        this.status = status;
    }

    @JsonProperty("statusDetail")
    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    @JsonProperty("num_results")
    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    @JsonProperty("total_results")
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @JsonProperty("results_remaining")
    public void setResultsRemaining(int resultsRemaining) {
        this.resultsRemaining = resultsRemaining;
    }

    @JsonProperty("matches")
    public void setMatches(List<MatchHistory> matches) {
        this.matches = matches;
    }


    //getter
    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    @JsonProperty("statusDetail")
    public String setStatusDetail() {
        return statusDetail;
    }

    @JsonProperty("num_results")
    public int getNumResults() {
        return numResults;
    }

    @JsonProperty("total_results")
    public int getTotalResults() {
        return totalResults;
    }

    @JsonProperty("results_remaining")
    public int getResultsRemaining() {
        return resultsRemaining;
    }

    @JsonProperty("matches")
    public List<MatchHistory> getMatches() {
        return matches;

    }
}

