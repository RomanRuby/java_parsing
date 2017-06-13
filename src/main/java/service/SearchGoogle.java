package service;

import models.dto.ResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Roman Nagibov
 */
public class SearchGoogle extends AbstractLinksSearch {

    private final static Logger LOGGER = LogManager.getLogger(SearchGoogle.class);
    private final static String GOOGLE_CSS_QUERY = "h3>a";
    private final static String GOOGLE_ATTRIBUTE_KEY = "href";


    public SearchGoogle(String mainRequest, String searchMessage) {
        super(mainRequest, searchMessage);
    }

    @Override
    public ResponseDto search() throws IOException {
        ResponseDto responseDto = null;
        try {
            String query = buildEncodedQuery();
            Document doc = Jsoup.connect(query).userAgent(USER_AGENT).get();
            Elements elements = doc.select(GOOGLE_CSS_QUERY);
            for (Element element : elements) {
                String absUrl = element.absUrl(GOOGLE_ATTRIBUTE_KEY);
                absUrl = decodeSearchString(absUrl);

                if (isConsistNews(absUrl)) {
                    continue;
                }
                String title = getTitle(absUrl);
                responseDto = new ResponseDto(title, absUrl);
            }

        } catch (HttpStatusException e) {
            LOGGER.info(e);
        }

        return responseDto;
    }

    private boolean isConsistNews(String url) {
        return (!url.startsWith("http"));
    }

    private String buildEncodedQuery() throws UnsupportedEncodingException {
        return mainRequest + encodeSearchString(searchMessage);
    }

    private String getTitle(String url) throws IOException {
        if (url.endsWith("pdf")) {
            return "This is pdf document";
        }

        return Jsoup.connect(url).userAgent(USER_AGENT).get().title();
    }

}
