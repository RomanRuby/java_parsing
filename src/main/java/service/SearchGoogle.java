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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Nagibov
 */
public class SearchGoogle extends AbstractSearchLinks {

    private static final Logger LOGGER = LogManager.getLogger(SearchGoogle.class.getName());
    private final static String googleCssQuery = "h3>a";
    private final static String googleAttributeKey = "href";


    public SearchGoogle(String mainRequest, String searchMessage) {
        super(mainRequest, searchMessage);
    }

    @Override
    public List<ResponseDto> search() throws IOException {
        List<ResponseDto> queryDtoList = new ArrayList<>();
        try {
            String request = mainRequest + EncodeSearchString(searchMessage);
            Document doc = Jsoup.connect(request).userAgent(USER_AGENT).get();
            Elements elements = doc.select(googleCssQuery);
            for (Element element : elements) {

                String absUrl = element.absUrl(googleAttributeKey);
                absUrl = DecodeSearchString(absUrl);
                /**
                 * Limit ads and news
                 */
                if (!absUrl.startsWith("http")) {
                    continue;
                }
                String title = processingPage(absUrl);
                queryDtoList.add(new ResponseDto(absUrl, title));

                if(referenceNumber == queryDtoList.size()){
                    break;
                }
            }

        } catch (HttpStatusException e) {
            LOGGER.info(e);
        }

        return queryDtoList;
    }

    private String processingPage(String url) throws IOException {
        if (url.endsWith("pdf")) {
            return "This is pdf document";
        }

        return Jsoup.connect(url).userAgent(USER_AGENT).get().title();
    }

}
