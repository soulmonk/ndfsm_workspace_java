package com.soulmonk.steamWeb.client.dota;

import com.soulmonk.steamWeb.client.UriUtils;
import com.soulmonk.steamWeb.client.dota.response.MatchHistoryResponse;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotaGetMatchHistoryRequest extends DotaRequest {
    private static final String STEAM_METHOD = "/GetMatchHistory";
    private static final String STEAM_METHOD_VERSION = "/V001";
    private static final Class RESPONSE_TYPE = MatchHistoryResponse.class;

    public DotaGetMatchHistoryRequest() {
        super();
        setSteamMethod(STEAM_METHOD);
        setSteamMethodVersion(STEAM_METHOD_VERSION);
        setResponseType(RESPONSE_TYPE);
    }


    public void setAccountId(String accountId) {
        parameters.put("account_id", accountId);
    }


    /*
    hero_id (Optional) (uint32)
A list of hero IDs can be found via the GetHeroes method.
game_mode (Optional) (uint32)
0 - None
1 - All Pick
2 - Captain's Mode
3 - Random Draft
4 - Single Draft
5 - All Random
6 - Intro
7 - Diretide
8 - Reverse Captain's Mode
9 - The Greeviling
10 - Tutorial
11 - Mid Only
12 - Least Played
13 - New Player Pool
14 - Compendium Matchmaking
16 - Captain's Draft
skill (Optional) (uint32)
Skill bracket for the matches (Ignored if an account ID is specified).
0 - Any
1 - Normal
2 - High
3 - Very High
date_min (Optional) (uint32)
Minimum date range for returned matches (unix timestamp, rounded to the nearest day).
date_max (Optional) (uint32)
Maximum date range for returned matches (unix timestamp, rounded to the nearest day).
min_players (Optional) (string)
Minimum amount of players in a match for the match to be returned.
account_id (Optional) (string)
32-bit account ID.
league_id (Optional) (string)
Only return matches from this league. A list of league IDs can be found via the GetLeagueListing method.
start_at_match_id (Optional) (string)
Start searching for matches equal to or older than this match ID.
matches_requested (Optional) (string)
Amount of matches to include in results (default: 25).
tournament_games_only (Optional) (string)
Whether to limit results to tournament matches. (0 = false, 1 = true)
     */
}

