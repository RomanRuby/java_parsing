package service;

/**
 * Created by Roman Nagibov
 */
public abstract class AbstractSearchLinks implements SearchLinks {

	protected String mainRequest;
	protected String searchMessage;
	protected String UserAgent;


	public AbstractSearchLinks(String mainRequest, String searchMessage, String UserAgent) {
		this.mainRequest = mainRequest;
		this.searchMessage = searchMessage;
		this.UserAgent = UserAgent;
	}

}
