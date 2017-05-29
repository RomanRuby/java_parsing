package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Roman Nagibov
 */
public class PropertiesSearch {

    public static final String SEARCH_GOOGLE = "http://www.google.com/search?q=";
    public static final String USER_AGENT = "Mozilla/5.0" + " Chrome/58.0.3029.110";
    private String searchMessage = null;
    private List<SearchEnum> searchEnumList;


    public PropertiesSearch() {
        searchEnumList = new ArrayList<>();
    }

    public void addEngineSearchList(SearchEnum searchList) {
        searchEnumList.add(searchList);
    }

    public List<SearchEnum> getSearchEnumList() {
        return searchEnumList;
    }

    public String getSearchMessage() {
        return searchMessage;
    }

    public void processingSearchMessage() {
        Scanner scanner = new Scanner(System.in);
        searchMessage = scanner.nextLine();
    }

    @Override
    public String toString() {
        return "PropertiesSearch{" +
                "searchMessage='" + searchMessage + '\'' +
                ", searchEnumList=" + searchEnumList +
                '}';
    }
}
