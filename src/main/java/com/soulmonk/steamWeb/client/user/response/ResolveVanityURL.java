package com.soulmonk.steamWeb.client.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 12:35 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResolveVanityURL {

    private String success;
    private String steamId;
    private String message;


    @JsonProperty("success")
    public String getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(String success) {
        this.success = success;
    }

    @JsonProperty("steamid")
    public String getSteamId() {
        return steamId;
    }

    @JsonProperty("steamid")
    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }
}
