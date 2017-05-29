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
    private final static String cssQuery = "h3>a";
    private final static String codeType = "UTF-8";
    private final static String attributeKey = "href";


    public SearchGoogle(String mainRequest, String searchMessage, String userAgent) {
        super(mainRequest, searchMessage, userAgent);
    }

    @Override
    public List<String> searchLinks() throws IOException {
        List<String> listURL = new ArrayList<>();
        try {
            String request = mainRequest + URLEncoder.encode(searchMessage, codeType);

            Document doc = Jsoup.connect(request).userAgent(PropertiesSearch.USER_AGENT).get();

            Elements elements = doc.select(cssQuery);
            for (Element elem : elements) {
                String line = elem.absUrl(attributeKey);
                line = URLDecoder.decode(line.substring(line.indexOf('=') + 1,
                        line.indexOf('&')), codeType);

                /**
                 * Limit ads and news
                 */
                if (!line.startsWith("http")) {
                    continue;
                }
                listURL.add(line);
            }
        } catch (HttpStatusException e) {
            e.printStackTrace();
        }

        return listURL;
    }

}
