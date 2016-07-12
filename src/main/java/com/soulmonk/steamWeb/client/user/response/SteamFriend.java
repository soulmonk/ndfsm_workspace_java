package com.soulmonk.steamWeb.client.user.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SteamFriend {

    public SteamFriend() {
    }

    private String steamId;
    private String relationship;
    private Long friendSince;
    private String steamName;
    private Long gamesWon;
    private Long totalGames;
    private BigDecimal winPercentage;
    private SteamPlayer steamPlayer;

    @JsonProperty("steamid")
    public String getSteamId() {
        return steamId;
    }

    @JsonProperty("steamid")
    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    @JsonProperty("relationship")
    public String getRelationship() {
        return relationship;
    }

    @JsonProperty("relationship")
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @JsonProperty("friend_since")
    public Long getFriendSince() {
        return friendSince;
    }

    @JsonProperty("friend_since")
    public void setFriendSince(Long friendSince) {
        this.friendSince = friendSince;
    }

    public void setFriendAlias(String steamName) {
        this.steamName = steamName;
    }

    public String getFriendAlias() {
        return steamName;
    }

    public Long getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Long totalGames) {
        this.totalGames = totalGames;
    }

    public BigDecimal getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(BigDecimal winPercentage) {
        this.winPercentage = winPercentage;
    }

    public Long getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Long gamesWon) {
        this.gamesWon = gamesWon;
    }

    public SteamPlayer getSteamPlayer() {
        return steamPlayer;
    }

    public void setSteamPlayer(SteamPlayer steamPlayer) {
        this.steamPlayer = steamPlayer;
    }
}
