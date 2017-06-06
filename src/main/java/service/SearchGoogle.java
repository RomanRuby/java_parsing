package service;

import models.dto.QueryDto;
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
    private final static String googleCssQuery = "h3>a";
    private final static String googleAttributeKey = "href";


    public SearchGoogle(String mainRequest, String searchMessage, String userAgent) {
        super(mainRequest, searchMessage, userAgent);
    }

    @Override
    public List<QueryDto> searchLinks() throws IOException {
        List<QueryDto> queryDtoList = new ArrayList<>();
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
                queryDtoList.add(new QueryDto(absUrl, title));
            }

        } catch (HttpStatusException e) {
            e.printStackTrace();
        }

        return queryDtoList;
    }

    private String processingPage(String url) {
        String title = null;
        if (url.endsWith("pdf")) {
            return "This is pdf document";
        }
        try {
            title = Jsoup.connect(url).userAgent(USER_AGENT).get().title();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return title;
    }

}
