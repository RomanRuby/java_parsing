package service;

import models.dto.ResponseDto;
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
    public List<ResponseDto> searchLinks() throws IOException {
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
            }

        } catch (HttpStatusException e) {
            e.printStackTrace();
        }

        return queryDtoList;
    }

    private String processingPage(String url) throws IOException {
        String title;
        if (url.endsWith("pdf")) {
            return "This is pdf document";
        }
            title = Jsoup.connect(url).userAgent(USER_AGENT).get().title();

        return title;
    }

}
