package com.soulmonk.steamWeb.shared;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HeroesList {

    public HeroesList() {
    }

    private String name;
    private int id;
    private String localizedName;


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("localized_name")
    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getCdnImg() {
        // npc_hero_dota_
        /*
1) tiny horizontal portrait - 35x20px (this one is no longer available)
<suffix> = eb.png
2) small horizontal portrait - 59x33px
<suffix> = sb.png
3) large horizontal portrait - 205x11px
<suffix> = lg.png
4) full quality horizontal portrait - 256x114px
<suffix> = full.png
5) full quality vertical portrait - 234x272px (note this is a .jpg)
<suffix> = vert.jpg
*/
        return "http://cdn.dota2.com/apps/dota2/images/heroes/" + name.substring(14) + "_lg.png";
    }
}
