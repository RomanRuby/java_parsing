package entity;

import service.AbstractSearchLinks;
import service.SearchGoogle;

/**
 * Created by Roman Nagibov
 */
public enum SearchEnum {

    GOOGLE {
        @Override
        public AbstractSearchLinks getSearchMethod(PropertiesSearch propertiesSearch) {

            return new SearchGoogle(PropertiesSearch.SEARCH_GOOGLE,
                    propertiesSearch.getSearchMessage(), PropertiesSearch.USER_AGENT);
        }
    };


    abstract public AbstractSearchLinks getSearchMethod(PropertiesSearch propertiesSearch);
}
