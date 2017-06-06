package models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Roman Nagibov
 */
@Data
public class PropertiesSearch {

    private String searchMessage = null;
    private List<SearchEnum> searchEnumList = new ArrayList<>();


    public void addEngineSearchList(SearchEnum searchList) {
        searchEnumList.add(searchList);
    }

    public void processingSearchMessage() {
        Scanner scanner = new Scanner(System.in);
        searchMessage = scanner.nextLine();
    }



}
