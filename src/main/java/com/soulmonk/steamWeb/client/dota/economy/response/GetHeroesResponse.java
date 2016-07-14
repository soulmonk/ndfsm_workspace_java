package com.soulmonk.steamWeb.client.dota.economy.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.soulmonk.steamWeb.client.base.WrapJsonRootElement;
import com.soulmonk.steamWeb.client.dota.economy.EconomyDotaRequest;
import com.soulmonk.steamWeb.shared.HeroesList;
import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 4:27 PM
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("result")
public class GetHeroesResponse implements WrapJsonRootElement {

    private List<HeroesList> heroesLists;
    private int count;
    private Map<Integer, HeroesList> mapHeroesLists = null;

    @JsonProperty("heroes")
    public void setHeroesLists(List<HeroesList> heroesLists) {
        this.heroesLists = heroesLists;
    }

    public List<HeroesList> getHeroesLists() {
        return heroesLists;
    }

    public Map<Integer, HeroesList> getMapHeroesLists() {
        if (mapHeroesLists != null) {
            return mapHeroesLists;
        }
        mapHeroesLists = new HashMap<>();

        for(HeroesList heroe: heroesLists) {
            mapHeroesLists.put(heroe.getId(), heroe);
        }

        return mapHeroesLists;
    }

    public int getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(int count) {
        this.count = count;
    }
}
