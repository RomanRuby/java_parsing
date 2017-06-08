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

    private DefaultEngineSearch() {
    }


    public static DefaultEngineSearch getInstance() {
        if (defaultEngineSearch == null) {
            defaultEngineSearch = new DefaultEngineSearch();
        }
        return defaultEngineSearch;
    }

    public List<ResponseDto> search(String message) throws IOException {
        SearchEnum searchEngine = SearchEnum.GOOGLE;
        SearchLinks searchLink = searchEngine.getSearchMethod(message);
        return searchLink.search();
    }

}
