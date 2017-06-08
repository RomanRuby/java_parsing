package service;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * Created by Roman Nagibov
 */
public abstract class AbstractSearchLinks implements SearchLinks {

	public static final String SEARCH_GOOGLE = "http://www.google.com/search?q=";
	public static final String USER_AGENT = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html";
	protected String mainRequest;
	protected String searchMessage;


	private final static String CodeType = "UTF-8";


	public AbstractSearchLinks(String mainRequest, String searchMessage) {
		this.mainRequest = mainRequest;
		this.searchMessage = searchMessage;
	}

	public String EncodeSearchString(String searchString) throws UnsupportedEncodingException {
		return URLEncoder.encode(searchString, CodeType);
	}

	public String DecodeSearchString(String absUrl) throws UnsupportedEncodingException {
		return URLDecoder.decode(absUrl.substring(absUrl.indexOf('=') + 1,
				absUrl.indexOf('&')), CodeType);
	}

}
