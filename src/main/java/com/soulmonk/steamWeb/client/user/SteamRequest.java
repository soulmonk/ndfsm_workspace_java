package com.soulmonk.steamWeb.client.user;

import org.apache.http.NameValuePair;

import java.util.List;

public interface SteamRequest {

	public String getSteamRoute();

	public String getSteamMethod();

	public String getSteamMethodVersion();
	
	public Class getResponseType();

	public List<NameValuePair> getSteamParameters();
}
