package com.soulmonk.steamWeb.client;

import com.soulmonk.steamWeb.persistence.HibernateUtil;
import com.soulmonk.steamWeb.shared.MatchDetail;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Singleton
 * Queries for existing Matches in DB
 * Stores as a list
 * Ability to add new match Ids or check existing oens
 */

public class MatchIdCache {
	
	private Set<Long> matchIdCache;
	private Long workingSeqNumber;
		
	private static MatchIdCache matchCache = new MatchIdCache();

	private MatchIdCache() {
	}

	public static MatchIdCache getInstance() {
		return matchCache;
	}

 
	
	public synchronized boolean  checkMatchId(Long matchId){
		return matchIdCache.contains(matchId);
	}
	
	
	public synchronized void addMatchId(Long matchId){
		this.matchIdCache.add(matchId);
	}

	public synchronized void setWorkingSeqNumber(Long seqNumber){
		this.workingSeqNumber = seqNumber;
	}
	public synchronized Long getWorkingSeqNumber(){
		return this.workingSeqNumber;
	}
	
	
	public synchronized void init() {
		
		if (matchIdCache == null) {
			matchIdCache = new HashSet<Long>();
			Session session = HibernateUtil.getSessionFactory().openSession();
//			Session session = Main.session.getSessionFactory().openSession();
			List<MatchDetail> matchDetails= (List<MatchDetail>)session.createCriteria(MatchDetail.class)
					.setProjection(
							Projections.projectionList()
									.add(Projections.property("matchId"),
											"matchId"))
					.setResultTransformer(
							Transformers.aliasToBean(MatchDetail.class))
							.list();
			
			for(MatchDetail matchDetail : matchDetails){
				matchIdCache.add(matchDetail.getMatchId());
			}

			session.close();
//			HibernateUtil.shutdown();

			System.out.println("Match ID Cache size:" + matchIdCache.size());
			
	}
}
}


