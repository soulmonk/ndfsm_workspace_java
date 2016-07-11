package com.soulmonk.steamWeb.client;

import com.soulmonk.steamWeb.client.PlayerConsumer.PlayerConsumerStatus;
import com.soulmonk.steamWeb.persistence.HibernateUtil;
import com.soulmonk.steamWeb.shared.MatchDetail;
import com.soulmonk.steamWeb.shared.MatchDetailPlayer;
import com.soulmonk.steamWeb.shared.MatchSeq;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MatchConsumer implements Runnable {
	private List<MatchDetail> matchDetails;
	
	public MatchConsumer(List<MatchDetail> matchDetails){
		this.matchDetails = matchDetails;
	}
	
    /**
     * Creates a new TaskExecutor 
     * Takes a list of matches
     * Filters out matches against matchIdCache
     * Persist the MatchDetail data in MatchResponse to the tables
     */
	
	
	public void run() {
		List<PlayerConsumerStatus> playerList = new ArrayList<PlayerConsumerStatus>();
		Set<Long> matchBlackList = new HashSet<Long>();
		
		ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new CustomThreadFactory("MatchConsumer"));
		
		
		try{
			for (Iterator<MatchDetail> iterator = matchDetails.iterator(); iterator.hasNext();) {
			    MatchDetail matchDetail = iterator.next();
			    //Check if Match already exists, add to blacklist if it does
			    if (MatchIdCache.getInstance().checkMatchId(matchDetail.getMatchId())) {
			        matchBlackList.add(matchDetail.getMatchId());
			    }
			    else{
			    	for(MatchDetailPlayer player : matchDetail.getPlayers()){
						//Add players to be consumed in PlayerConsumer
			    		playerList.add(new PlayerConsumerStatus(player.getSteamId(), matchDetail.getMatchId()));
			    		
//			    		System.out.println("Match Consumer list of players in list of matches: " + player.getSteamId());
			    		
			    	}

			    }
			}
			
			
			Future<List<PlayerConsumerStatus>> future = taskExecutor.submit(new PlayerConsumer(playerList));
//			Thread.sleep(3000);     
			
		

			List<PlayerConsumerStatus> statusList = future.get();

			//If there are players that did not get pushed to db, do not consume match	
			for(PlayerConsumerStatus status : statusList){
				if (!status.isSuccess()){	
					matchBlackList.add(status.getMatchId());
			}
			}
			

			for(MatchDetail detail : matchDetails ){
				if(!matchBlackList.contains(detail.getMatchId())){
					Session session = null;
					
					MatchSeq matchSequenceTracker = new MatchSeq();
					matchSequenceTracker.setMatchId(detail.getMatchId());
					matchSequenceTracker.setMatchSeqId(detail.getMatchSeqNum());
					matchSequenceTracker.setTimeUpdated(new Timestamp(System.currentTimeMillis()));
					try{
						session =  HibernateUtil.getSessionFactory().openSession();
						
						session.beginTransaction(); 
						session.save(detail);       //commit MatchResult data to MatchDetail/MatchDetailPlayer tables
						session.save(matchSequenceTracker);
					//	System.out.println("MatchSeq saved:" + matchSequenceTracker.getMatchId());
						session.getTransaction().commit();
						MatchIdCache.getInstance().addMatchId(detail.getMatchId());
					//	System.out.println("Consumed MatchId = " + detail.getMatchId());
					//	System.out.println("Corresponding MatchSeq: " + detail.getMatchSeqNum());
					}
					catch(ConstraintViolationException e){
						System.out.println("did not consume: " + detail.getMatchId()+ "," +  matchSequenceTracker.getMatchSeqId());
						session.getTransaction().rollback();
					}
					catch(Exception e){
						e.printStackTrace();
					}
					finally{
						if (session != null){
						session.close();
						}
					}
				}
			}






		}

		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			taskExecutor.shutdown(); //Shutdown task

		}
	}
	

}

