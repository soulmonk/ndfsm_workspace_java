package com.soulmonk.steamWeb.client.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SteamFriendsListResponse {

    private SteamFriendsList friendsList;

    public SteamFriendsListResponse() {

    }

    @JsonProperty("friendslist")
    public SteamFriendsList getFriendsList() {
        return friendsList;
    }

    @JsonProperty("friendslist")
    public void setFriendsList(SteamFriendsList friendsList) {
        this.friendsList = friendsList;
    }


}

