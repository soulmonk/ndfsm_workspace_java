package com.soulmonk.ndfsm.web.app.controllers.steamWeb;

import com.soulmonk.steamWeb.client.SteamApi;
import com.soulmonk.steamWeb.client.apps.GetSteamAppListRequest;
import com.soulmonk.steamWeb.client.apps.response.SteamApp;
import com.soulmonk.steamWeb.client.apps.response.SteamAppListResponse;
import com.soulmonk.steamWeb.client.user.ResolveVanityURLRequest;
import com.soulmonk.steamWeb.client.user.SteamGetFriendsListRequest;
import com.soulmonk.steamWeb.client.user.SteamGetPlayerSummaryRequest;
import com.soulmonk.steamWeb.client.user.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User: SoulMonk
 * Date: 7/11/16
 * Time: 10:28 PM
 */

@Controller
@RequestMapping(value = "/steamWeb")
public class SteamWebController {
    private static final Logger logger = LoggerFactory.getLogger(SteamWebController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model uiModel) {
        logger.info("Base index steam web");
        return "steamWeb/index";
    }

    @RequestMapping(value = "/resolve-steam-id", method = RequestMethod.GET)
    public String resolveSteamId(Model uiModel) {
        logger.info("Base index steam web");

        uiModel.addAttribute("userVanityUrlName", "");
        return "steamWeb/resolve-steam";
    }

    @RequestMapping(value = "/resolve-steam-id", method = RequestMethod.POST)
    public String resolveSteamId(Model uiModel, @RequestParam("userVanityUrlName") String userVanityUrlName) {
        logger.info("Base index steam web");

        SteamApi steamApi = new SteamApi();

        ResolveVanityURLRequest request = new ResolveVanityURLRequest();
        request.setVanityurl(userVanityUrlName);

        ResolveVanityURLResponse response = (ResolveVanityURLResponse) steamApi.execute(request);
        uiModel.addAttribute("response", response.getResponse());
        uiModel.addAttribute("userVanityUrlName", userVanityUrlName);

        return "steamWeb/resolve-steam";
    }

    @RequestMapping(value = "/steam-summaries", method = RequestMethod.GET)
    public String steamSummaries(Model uiModel) {
        logger.info("steamSummaries");

        uiModel.addAttribute("steamId", "");
        return "steamWeb/steam-summaries";
    }

    @RequestMapping(value = "/steam-summaries", method = RequestMethod.POST)
    public String steamSummaries(Model uiModel, @RequestParam("steamId") String steamId) {
        logger.info("steamSummaries");

        SteamApi steamApi = new SteamApi();
        SteamGetPlayerSummaryRequest request = new SteamGetPlayerSummaryRequest();
        request.setSteamId(steamId);

        SteamPlayerSummary response = (SteamPlayerSummary)steamApi.execute(request);


        uiModel.addAttribute("steamPlayer", response.getResponse().getPlayers().get(0));
        uiModel.addAttribute("steamId", steamId);

        return "steamWeb/steam-summaries";
    }

    @RequestMapping(value = "/app-list", method = RequestMethod.GET)
    public String appList(Model uiModel) {
        logger.info("Listing steam aps");

        SteamApi steamApi = new SteamApi();
        GetSteamAppListRequest getSteamAppListRequest = new GetSteamAppListRequest();

        SteamAppListResponse steamAppListResponse = (SteamAppListResponse) steamApi.execute(getSteamAppListRequest);

        List<SteamApp> steamApps = steamAppListResponse.getSteamAppList().getSteamApps();

        uiModel.addAttribute("steamApps", steamApps);

        logger.info("No. of steamApps: " + steamApps.size());

        return "steamWeb/app-list";
    }

    @RequestMapping(value = "/friend-list", method = RequestMethod.POST)
    public String friendList(Model uiModel, @RequestParam("steamId") String steamId) {
        logger.info("Listing friends");
        logger.info("SteamId: " + steamId);

        SteamApi steamApi = new SteamApi();
        SteamGetFriendsListRequest friendsListRequest = new SteamGetFriendsListRequest();
        friendsListRequest.setSteamId(steamId);

        SteamFriendsListResponse steamFriendsListResponse = (SteamFriendsListResponse) steamApi.execute(friendsListRequest);

        List<SteamFriend> friends = steamFriendsListResponse.getFriendsList().getSteamFriends();
        SteamGetPlayerSummaryRequest steamGetPlayerSummaryRequest = new SteamGetPlayerSummaryRequest();
        Map<String, SteamFriend> steamFriendMap = new HashMap<>();
        for (SteamFriend friend : steamFriendsListResponse.getFriendsList().getSteamFriends()) {
            steamFriendMap.put(friend.getSteamId(), friend);
            steamGetPlayerSummaryRequest.addSteamId(friend.getSteamId());
        }
        SteamPlayerSummary playerSummaryResponse = (SteamPlayerSummary) steamApi
                .execute(steamGetPlayerSummaryRequest);

        for (SteamPlayer player : playerSummaryResponse
                .getResponse().getPlayers()) {
            SteamFriend friend = steamFriendMap.get(player.getSteamId());
            friend.setSteamPlayer(player);
        }

        uiModel.addAttribute("friends", friends);

        logger.info("No. of notifications: ");

        return "steamWeb/friend-list";
    }

}