package models;

import service.AbstractSearchLinks;
import service.SearchGoogle;

import static service.AbstractSearchLinks.SEARCH_GOOGLE;

/**
 * Created by Roman Nagibov
 */
public enum SearchEnum {

    GOOGLE {
        @Override
        public AbstractSearchLinks getSearchMethod(String message) {
            return new SearchGoogle(
                    SEARCH_GOOGLE, message
            );
        }
    };


    abstract public AbstractSearchLinks getSearchMethod(String message);

}
