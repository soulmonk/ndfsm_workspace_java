package com.soulmonk.steamWeb.client.dota.economy.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: SoulMonk
 * Date: 7/14/16
 * Time: 7:20 PM
 */
public class GameItem {
    private Integer id; //ID of the hero.
    private String name; //The tokenized string for the name of the hero.
    private String cost; //The in-game gold cost of the item.
    private Boolean secretShop; //Boolean - true if the item is only available in the secret shop.
    private Boolean sideShop; //Boolean - true if the item is available in the side shop.
    private String recipe; //Boolean - true if the item is a recipe type item.
    private String localizedName; //The localized name of the hero for use in name display.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public Boolean getSecretShop() {
        return secretShop;
    }
    @JsonProperty("secret_shop")
    public void setSecretShop(Boolean secretShop) {
        this.secretShop = secretShop;
    }

    public Boolean getSideShop() {
        return sideShop;
    }
    @JsonProperty("side_shop")
    public void setSideShop(Boolean sideShop) {
        this.sideShop = sideShop;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    @JsonProperty("localized_name")
    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getCdnImg() {
        // http://cdn.steamstatic.com/apps/dota2/images/items/blink_lg.png
        return "http://cdn.steamstatic.com/apps/dota2/images/items/" + name.substring(5) + "_lg.png";
    }
}
