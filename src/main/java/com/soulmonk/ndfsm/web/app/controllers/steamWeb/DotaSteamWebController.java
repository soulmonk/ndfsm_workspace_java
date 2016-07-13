package com.soulmonk.ndfsm.web.app.controllers.steamWeb;

import com.soulmonk.steamWeb.client.SteamApi;
import com.soulmonk.steamWeb.client.dota.DotaGetMatchHistoryRequest;
import com.soulmonk.steamWeb.client.dota.response.MatchHistoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

        return "steamWeb/dota2/match-history";
    }
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public String index(Model uiModel, @RequestParam("steamId") String steamId) {
        logger.info("dota2 index");


        SteamApi steamApi = new SteamApi();

        DotaGetMatchHistoryRequest request = new DotaGetMatchHistoryRequest();
        request.setAccountId(request.getAccountIdFromSteamId(steamId));

        MatchHistoryResponse response = (MatchHistoryResponse)steamApi.execute(request);

        uiModel.addAttribute("steamId", steamId);

        return "steamWeb/dota2/match-history";
    }
}
