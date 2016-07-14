package com.soulmonk.steamWeb.client.dota.economy.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.soulmonk.steamWeb.client.base.WrapJsonRootElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 7:17 PM
 */
@JsonRootName(value = "result")
public class GetGameItemsResponse implements WrapJsonRootElement {
    private List<GameItem> gameItems;
    private Map<Integer, GameItem> mapGameItems = null;

    private String status;

    @JsonProperty("items")
    public void setGameItems(List<GameItem> gameItems) {
        this.gameItems = gameItems;
    }

    public List<GameItem> setGameItems() {
        return gameItems;
    }

    public Map<Integer, GameItem> getMapGameItems() {
        if (mapGameItems != null ) {
            return mapGameItems;
        }
        mapGameItems = new HashMap<>();
        for(GameItem item: gameItems) {
            mapGameItems.put(item.getId(), item);
        }
        return mapGameItems;
    }

    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }
}
