package service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Roman Nagibov
 */
public abstract class AbstractSearchLinks implements SearchLinks {

	protected String mainRequest;
	protected String searchMessage;
	protected String UserAgent;
	public static final String MENU = " \n Enter your option : \n Press 1 - start \n 2 - end \n Input number:";
	public static final String GREETING = "Please, enter your request:";
	public static final String SEARCH_GOOGLE = "http://www.google.com/search?q=";
	public static final String USER_AGENT = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html";
	private final static String CodeType = "UTF-8";


	public AbstractSearchLinks(String mainRequest, String searchMessage, String UserAgent) {
		this.mainRequest = mainRequest;
		this.searchMessage = searchMessage;
		this.UserAgent = UserAgent;
	}

	public String EncodeSearchString(String searchString) throws UnsupportedEncodingException {
		return URLEncoder.encode(searchString, CodeType);
	}

	public String DecodeSearchString(String absUrl) throws UnsupportedEncodingException {
		return URLDecoder.decode(absUrl.substring(absUrl.indexOf('=') + 1,
				absUrl.indexOf('&')), CodeType);
	}

}
