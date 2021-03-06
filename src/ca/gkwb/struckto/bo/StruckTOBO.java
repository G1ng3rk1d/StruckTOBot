package ca.gkwb.struckto.bo;


import java.sql.Date;
import java.util.List;

import ca.gkwb.struck.incident.dao.IncidentVO;
import ca.gkwb.struck.location.dao.LocationVO;
import ca.gkwb.struck.tweet.dao.TweetVO;
import ca.gkwb.struckto.exception.FatalException;
import ca.gkwb.struckto.exception.WarnException;
import twitter4j.Status;

public interface StruckTOBO {
	
	public int queryAndProcess(String targetAcct, int size, List<String> hashTags) throws FatalException;
	
	/**
	 * Polls twitter for new incidents and returns a list of struckTOincidentsVOs. Builds struckTOIncidentVOs from
	 * google maps API.
	 * 
	 * @author gingerk1d
	 * @date 2015-12-02
	 * @return
	 * @throws FatalException
	 * @throws WarnException
	 */
	public List<IncidentVO> getNewIncidents() throws FatalException, WarnException;
	
	/**
	 * Returns a StruckTOLocationVO that matches the input StruckTOIncidentVO.
	 * 
	 * @author gingerk1d
	 * @date 2015-12-02
	 * @param stVO
	 * @return StruckTOLocationVO
	 * @throws WarnException
	 * @throws FatalException
	 */
	public LocationVO getLocationVOForIncident(IncidentVO stVO) throws WarnException, FatalException;
	
	public String buildHashtagString(List<String> htags);
	
	public List<String> parseHashtags(String hashtagString);
	
	/**
	 * Helper method that generates a StruckTOTweetVO object from the input date and status
	 * 
	 * @author gingerk1d
	 * @date 2016-06-07
	 * @param Status
	 * @param Date
	 * @return StruckTOTweetVO
	 */
	public TweetVO generateTweetVO(Status s, Date d);

	/**
	 * Helper method that generates a StruckTOIncidentVO object from the input date, locationId, and tweetId
	 * 
	 * @author gingerk1d
	 * @date 2016-06-07
	 * @param Status
	 * @param Date
	 * @paraml Integer
	 * @return StruckTOIncidentVO
	 */	
	public IncidentVO generateIncidentVO(long tweetId, Date date, Integer locationId);
	
	/**
	 * Processes a single incident into the struckTO db and google maps API. Returns true if processed
	 * successfully.
	 * 
	 * @author gingerk1d
	 * @date 2015-12-02
	 * @param status
	 * @return boolean 
	 * @throws WarnException
	 * @throws FatalException
	 */	
	public boolean processIncident(Status s) throws FatalException;
	
	/**
	 * Process a single status and returns a location VO
	 * 
	 * @author gingerk1d
	 * @date 2016-08-04
	 * @param Status
	 * @return 
	 * @throws FatalException
	 */
	public LocationVO generateLocationVO(Status s) throws FatalException;
	
	public List<IncidentVO> getIncidentsByLocationVO(LocationVO locationVO) throws FatalException;

}
