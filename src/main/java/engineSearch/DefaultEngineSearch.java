package engineSearch;

import entity.PropertiesSearch;
import entity.SearchEnum;
import org.jsoup.Jsoup;
import service.SearchLinks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Nagibov
 */
public class DefaultEngineSearch {

    private PropertiesSearch prop;


    public DefaultEngineSearch() {
    }

    public void setProp(PropertiesSearch prop) {
        this.prop = prop;
    }

    public List<String> search() {
        List<String> resultList = new ArrayList<>();
        ArrayList<SearchLinks> searchLinks = new ArrayList<>();
        List<SearchEnum> searchList = prop.getSearchEnumList();

        for (SearchEnum linkSearch : searchList) {
            searchLinks.add(linkSearch.getSearchMethod(this.prop));
        }

        for (SearchLinks searchLink : searchLinks) {

            List<String> listString = null;
            try {
                listString = searchLink.searchLinks();
            } catch (IOException e) {
                e.printStackTrace();
            }
            resultList.addAll(listString);

        }

        return resultList;
    }

    public String processingPage(String url, String UserAgent) {
        String title = null;

        try {
            title = Jsoup.connect(url).userAgent(UserAgent).get().title();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return title;
    }

}
