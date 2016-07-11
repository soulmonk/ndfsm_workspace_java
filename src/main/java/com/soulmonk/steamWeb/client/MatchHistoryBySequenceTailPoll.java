package com.soulmonk.steamWeb.client;

import com.soulmonk.steamWeb.client.dota.DotaGetMatchDetailsRequest;
import com.soulmonk.steamWeb.client.dota.DotaGetMatchHistoryBySequenceRequest;
import com.soulmonk.steamWeb.client.dota.response.MatchDetailResponse;
import com.soulmonk.steamWeb.client.dota.response.MatchHistoryBySequenceResponse;
import com.soulmonk.steamWeb.persistence.HibernateUtil;
import com.soulmonk.steamWeb.shared.MatchDetail;
import com.soulmonk.steamWeb.shared.MatchSeq;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MatchHistoryBySequenceTailPoll implements Runnable {

	public MatchHistoryBySequenceTailPoll(){
		
	}
	public void run(){
		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchConsumer"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		SteamApi api = new SteamApi(SteamKeys.getSteamKey());
		Long workingSequenceNumber = (long) 0;
		
		try{
			
			
		if (MatchIdCache.getInstance().getWorkingSeqNumber() == null){
			SQLQuery matchSeqQuery = session
					.createSQLQuery("select * from match_seq where time_updated is not null order by time_updated desc limit 1;")
					.addEntity(MatchSeq.class);
			List matchSeqQueryResult = matchSeqQuery.list();
//			MatchSeq matchSequence = (MatchSeq) matchSeqQueryResult.get(0);
//			workingSequenceNumber = matchSequence.getMatchSeqId();
	   for (Iterator iterator =
			matchSeqQueryResult.iterator(); iterator.hasNext();){
	    MatchSeq matchSequence = (MatchSeq) iterator.next(); 
	    System.out.print("Match Sequence : " + matchSequence.getMatchSeqId());
	    workingSequenceNumber = matchSequence.getMatchSeqId();
	 }
//			
		}
		else{
			workingSequenceNumber = MatchIdCache.getInstance().getWorkingSeqNumber();
		}
		
		
		DotaGetMatchHistoryBySequenceRequest request = new DotaGetMatchHistoryBySequenceRequest();
		//Grab latest sequence number from db
		
		
		List<MatchDetail> matchResults;
		Long historicSequenceNumber = workingSequenceNumber - 400;
		request.setSequenceNumber(String.valueOf(historicSequenceNumber));
		System.out.println("initial historicSequence #: " + historicSequenceNumber);
		MatchHistoryBySequenceResponse matchHistorySequenceResponse = (MatchHistoryBySequenceResponse) api
				.execute(request);
		matchResults = matchHistorySequenceResponse.getResult().getMatches();
		
		
		while(matchResults.get(0).getMatchSeqNum() > (workingSequenceNumber - 5000)){
			for (MatchDetail result : matchResults){
				SQLQuery matchSeqQuery = session
						.createSQLQuery("select * from match_detail where match_id = " + result.getMatchId())
						.addEntity(MatchDetail.class);
				List matchSeqQueryResult = matchSeqQuery.list();
		   for (Iterator iterator =
				matchSeqQueryResult.iterator(); iterator.hasNext();){
		    MatchDetail matchDetail = (MatchDetail) iterator.next(); 
		    //Iterate through results and grab new matchDetails for nonexisting matches
		    if (matchDetail == null){
		    	DotaGetMatchDetailsRequest matchDetailRequest = new DotaGetMatchDetailsRequest();
		    	MatchDetailResponse matchDetailResponse= (MatchDetailResponse) api.execute(matchDetailRequest);
		    	if (matchDetailResponse != null){
		    		List<MatchDetail> matchToBeConsumed = new ArrayList<MatchDetail>();
		    		taskExecutor.submit(new MatchConsumer(matchToBeConsumed));		    		
		    	}
		    
		    }
		
	    }
	}
			historicSequenceNumber -= 100;
			Thread.sleep(1000);
			DotaGetMatchHistoryBySequenceRequest historicRequest = new DotaGetMatchHistoryBySequenceRequest();
			historicRequest.setSequenceNumber(String.valueOf(historicSequenceNumber));
			MatchHistoryBySequenceResponse matchHistorySequenceResponseScan = (MatchHistoryBySequenceResponse) api
					.execute(historicRequest);
			Thread.sleep(1000);
			matchResults = matchHistorySequenceResponseScan.getResult().getMatches();
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			taskExecutor.shutdown();
			System.out.println("shutdown!!!!");
		}
	
	}
}
