package models;

import service.AbstractLinksSearch;
import service.SearchGoogle;

import static service.AbstractLinksSearch.SEARCH_GOOGLE;

/**
 * Created by Roman Nagibov
 */
public enum SearchEnum {

    GOOGLE {
        @Override
        public AbstractLinksSearch getSearchMethod(String message) {
            return new SearchGoogle(
                    SEARCH_GOOGLE, message
            );
        }
    };


    abstract public AbstractLinksSearch getSearchMethod(String message);

}
