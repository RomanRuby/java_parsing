package service;

import entity.PropertiesSearch;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Nagibov
 */
public class SearchGoogle extends AbstractSearchLinks {
    private final static String googleCssQuery = "h3>a";
    private final static String googleCodeType = "UTF-8";
    private final static String googleAttributeKey = "href";


    public SearchGoogle(String mainRequest, String searchMessage, String userAgent) {
        super(mainRequest, searchMessage, userAgent);
    }

    @Override
    public List<String> searchLinks() throws IOException {
        List<String> listURL = new ArrayList<>();
        try {
            String request = mainRequest + URLEncoder.encode(searchMessage, googleCodeType);

            Document doc = Jsoup.connect(request).userAgent(PropertiesSearch.USER_AGENT).get();

            Elements elements = doc.select(googleCssQuery);
            for (Element element : elements) {

                String absUrl = element.absUrl(googleAttributeKey);
                absUrl = URLDecoder.decode(absUrl.substring(absUrl.indexOf('=') + 1,
                        absUrl.indexOf('&')), googleCodeType);

                /**
                 * Limit ads and news
                 */
                if (!absUrl.startsWith("http")) {
                    continue;
                }
                listURL.add(absUrl);
            }

        } catch (HttpStatusException e) {
            e.printStackTrace();
        }

        return listURL;
    }

}
