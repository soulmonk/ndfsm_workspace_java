package com.soulmonk.steamWeb.client;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 5:06 PM
 */
public class SteamHelpers {

    public static String getAccountIdFromSteamId(String steamId) {
        String accountId = "";
        try {
            accountId = (Long.parseLong(steamId) - 76561197960265728L)
                    + "";
        } catch (NumberFormatException e) {
            // TODO
        }
        return accountId;
    }

    public static String getSteamIdFromAccountId(String accountId) {
        Long accountIdLong = Long.parseLong(accountId);
        String steamId = "";
        if (accountIdLong != Long.parseLong("4294967295")) {
            long steam64 = accountIdLong + (Long.parseLong("76561197960265728"));
            steamId = steam64 + "";
        }
        return steamId;
    }
}
