package com.soulmonk.ndfsm.web.app.controllers.steamWeb;

import com.soulmonk.steamWeb.client.SteamApi;
import com.soulmonk.steamWeb.client.SteamHelpers;
import com.soulmonk.steamWeb.client.dota.DotaGetMatchDetailsRequest;
import com.soulmonk.steamWeb.client.dota.DotaGetMatchHistoryRequest;
import com.soulmonk.steamWeb.client.dota.economy.GetGameItemsRequest;
import com.soulmonk.steamWeb.client.dota.economy.GetHeroesRequest;
import com.soulmonk.steamWeb.client.dota.economy.GetItemIconPathRequest;
import com.soulmonk.steamWeb.client.dota.economy.response.GetGameItemsResponse;
import com.soulmonk.steamWeb.client.dota.economy.response.GetHeroesResponse;
import com.soulmonk.steamWeb.client.dota.economy.response.GetItemIconPathResponse;
import com.soulmonk.steamWeb.client.dota.response.MatchDetailResponse;
import com.soulmonk.steamWeb.client.dota.response.MatchHistoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 3:06 PM
 */
@Controller
@RequestMapping(value = "/steamWeb/dota2")
public class DotaSteamWebController {

    private static final Logger logger = LoggerFactory.getLogger(DotaSteamWebController.class);

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model uiModel) {
        logger.info("dota2 index");

        uiModel.addAttribute("steamId", "");
        uiModel.addAttribute("currentPage", 0);

        return "steamWeb/dota2/match-history";
    }
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public String index(Model uiModel, @RequestParam("steamId") String steamId, @RequestParam(value = "currentPage", required = false, defaultValue = "0") Integer currentPage) {
        logger.info("dota2 index");


        SteamApi steamApi = new SteamApi();

        DotaGetMatchHistoryRequest request = new DotaGetMatchHistoryRequest();
        request.setAccountId(SteamHelpers.getAccountIdFromSteamId(steamId));

        MatchHistoryResponse response = (MatchHistoryResponse)steamApi.execute(request);

        uiModel.addAttribute("steamId", steamId);
        uiModel.addAttribute("matchHistory", response.getResult());
        uiModel.addAttribute("currentPage", currentPage);

        return "steamWeb/dota2/match-history";
    }

    @RequestMapping(value = "match-details/{id}", method = RequestMethod.GET)
    public String matchDetails(Model uiModel, @PathVariable("id") String matchId) {
        logger.info("dota2 match-details: " + matchId);

        SteamApi steamApi = new SteamApi();

        GetHeroesRequest heroesRequest = new GetHeroesRequest();
        GetHeroesResponse heroesResponse = (GetHeroesResponse) steamApi.execute(heroesRequest);

        GetGameItemsRequest gameItemRequest = new GetGameItemsRequest();
        GetGameItemsResponse gameItemResponse = (GetGameItemsResponse) steamApi.execute(gameItemRequest);


        DotaGetMatchDetailsRequest matchDetailsRequest = new DotaGetMatchDetailsRequest(matchId);
        MatchDetailResponse matchDetailResponse = (MatchDetailResponse) steamApi.execute(matchDetailsRequest);

        uiModel.addAttribute("matchId", matchId);
        uiModel.addAttribute("heroes", heroesResponse.getMapHeroesLists());
        uiModel.addAttribute("items", gameItemResponse.getMapGameItems());
        uiModel.addAttribute("matchDetail", matchDetailResponse.getResult());

        return "steamWeb/dota2/match-details";
    }
}
