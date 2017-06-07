package engineSearch;

import lombok.Data;
import models.SearchEnum;
import models.dto.ResponseDto;
import service.SearchLinks;

import java.io.IOException;
import java.util.List;

/**
 * Created by Roman Nagibov
 */
@Data
public class DefaultEngineSearch {

    private static DefaultEngineSearch defaultEngineSearch;

    public static DefaultEngineSearch getInstance() {
        if (defaultEngineSearch == null) {
            defaultEngineSearch = new DefaultEngineSearch();
        }
        return defaultEngineSearch;
    }

    private DefaultEngineSearch() {
    }


    public List<ResponseDto> search(String message) throws IOException {
        SearchEnum searchList = SearchEnum.GOOGLE;
        SearchLinks searchLinks = (searchList.getSearchMethod(message));
        return searchLinks.searchLinks();
    }

}
