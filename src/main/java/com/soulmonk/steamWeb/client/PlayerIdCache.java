package com.soulmonk.steamWeb.client;

import com.soulmonk.steamWeb.client.user.response.SteamPlayer;
import com.soulmonk.steamWeb.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Singleton
 * Queries for existing Steam Ids in DB
 * Stores as a list
 * Ability to add new steam Ids or check existing oens
 */

public class PlayerIdCache {
	
	private Set<String> playerIdCache;
		
	private static PlayerIdCache playerCache = new PlayerIdCache();

	private PlayerIdCache() {
	}

	public static PlayerIdCache getInstance() {
		return playerCache;
	}

 
	
	public synchronized boolean  checkPlayerId(String PlayerId){
		return playerIdCache.contains(PlayerId);
	}
	
	public synchronized boolean  checkPlayerIds(List PlayerId){
		return playerIdCache.contains(PlayerId);
	}
	
	public synchronized void addPlayerId(String PlayerId){
		this.playerIdCache.add(PlayerId);
	}

	
	public synchronized void init() {
		
		if (playerIdCache == null) {
			playerIdCache = new HashSet<String>();
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			List<SteamPlayer> PlayerDetails= (List<SteamPlayer>)session.createCriteria(SteamPlayer.class)
					.setProjection(
							Projections.projectionList()
									.add(Projections.property("steamId"),
											"steamId"))
					.setResultTransformer(
							Transformers.aliasToBean(SteamPlayer.class))
							.list();
			
			for(SteamPlayer PlayerDetail : PlayerDetails){
				playerIdCache.add(PlayerDetail.getSteamId());
			}
			session.close();
//			HibernateUtil.shutdown();
		}

	}
}


