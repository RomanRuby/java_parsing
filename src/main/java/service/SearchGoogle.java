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
public class SearchGoogle extends AbstractSearchLinks {

    private static final Logger LOGGER = LogManager.getLogger(SearchGoogle.class);
    private final static String googleCssQuery = "h3>a";
    private final static String googleAttributeKey = "href";


    public SearchGoogle(String mainRequest, String searchMessage) {
        super(mainRequest, searchMessage);
    }

    @Override
    public ResponseDto search() throws IOException {
        ResponseDto responseDto = null;
        try {
            String query = installEncodingQuery();
            Document doc = Jsoup.connect(query).userAgent(USER_AGENT).get();
            Elements elements = doc.select(googleCssQuery);
            for (Element element : elements) {
                String absUrl = element.absUrl(googleAttributeKey);
                absUrl = decodeSearchString(absUrl);

                if (isUrlConsistNews(absUrl)) {
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

    private boolean isUrlConsistNews(String url) {
        return (!url.startsWith("http"));
    }

    private String installEncodingQuery() throws UnsupportedEncodingException {
        return mainRequest + encodeSearchString(searchMessage);
    }

    private String getTitle(String url) throws IOException {
        if (url.endsWith("pdf")) {
            return "This is pdf document";
        }

        return Jsoup.connect(url).userAgent(USER_AGENT).get().title();
    }

}
