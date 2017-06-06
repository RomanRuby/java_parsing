package models;

import service.AbstractSearchLinks;
import service.SearchGoogle;

import static service.AbstractSearchLinks.SEARCH_GOOGLE;
import static service.AbstractSearchLinks.USER_AGENT;

/**
 * Created by Roman Nagibov
 */
public enum SearchEnum {

    GOOGLE {
        @Override
        public AbstractSearchLinks getSearchMethod(PropertiesSearch propertiesSearch) {

            return new SearchGoogle(
                    SEARCH_GOOGLE, propertiesSearch.getSearchMessage(),USER_AGENT
            );
        }

    };


    abstract public AbstractSearchLinks getSearchMethod(PropertiesSearch propertiesSearch);
}
