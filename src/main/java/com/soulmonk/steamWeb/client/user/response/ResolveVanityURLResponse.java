package com.soulmonk.steamWeb.client.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 12:35 PM
 */
public class ResolveVanityURLResponse {
    private ResolveVanityURL response;

    @JsonProperty("response")
    public ResolveVanityURL getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(ResolveVanityURL response) {
        this.response = response;
    }

}
