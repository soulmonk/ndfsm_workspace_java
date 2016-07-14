package com.soulmonk.steamWeb.client.dota.constants;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 4:14 PM
 */
public class LeaverStatus {
    public static final Integer NONE = 0; // finished match, no abandon.
    public static final Integer DISCONNECTED = 1; // player DC, no abandon.
    public static final Integer DISCONNECTED_TOO_LONG = 2; // player DC > 5min, abandoned.
    public static final Integer ABANDONED = 3; // player DC, clicked leave, abandoned.
    public static final Integer AFK = 4; // player AFK, abandoned.
    public static final Integer NEVER_CONNECTED = 5; // player never connected, no abandon.
    public static final Integer NEVER_CONNECTED_TOO_LONG = 6; // player took too long to connect, no abandon.
}
