package com.soulmonk.steamWeb.client.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SteamFriendsList {

    public SteamFriendsList() {
    }

    private List<SteamFriend> steamFriends;


    @JsonProperty("friends")
    public List<SteamFriend> getSteamFriends() {
        return steamFriends;
    }

    @JsonProperty("friends")
    public void setSteamFriends(List<SteamFriend> steamFriends) {
        this.steamFriends = steamFriends;
    }


}
