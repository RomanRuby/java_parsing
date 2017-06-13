package models;

import service.AbstractLinksSearch;
import service.GoogleSearch;

import static service.AbstractLinksSearch.SEARCH_GOOGLE;

/**
 * Created by Roman Nagibov
 */
public enum EnumSearch {

    GOOGLE {
        @Override
        public AbstractLinksSearch getSearchMethod(String message) {
            return new GoogleSearch(
                    SEARCH_GOOGLE, message
            );
        }
    };


    abstract public AbstractLinksSearch getSearchMethod(String message);

}
