package engineSearch;

import models.PropertiesSearch;
import models.SearchEnum;
import lombok.Data;
import models.dto.QueryDto;
import service.SearchLinks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman Nagibov
 */
@Data
public class DefaultEngineSearch {

    private PropertiesSearch propertiesSearch;


    public List<QueryDto> search() {
        List<QueryDto> resultList = new ArrayList<>();
        ArrayList<SearchLinks> searchLinks = new ArrayList<>();
        List<SearchEnum> searchList = propertiesSearch.getSearchEnumList();

        for (SearchEnum linkSearch : searchList) {
            searchLinks.add(linkSearch.getSearchMethod(propertiesSearch));
        }

        for (SearchLinks searchLink : searchLinks) {

            List<QueryDto> listString = null;
            try {
                listString = searchLink.searchLinks();
            } catch (IOException e) {
                e.printStackTrace();
            }
            resultList.addAll(listString);

        }

        return resultList;
    }



}
