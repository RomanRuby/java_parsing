package service;

import engineSearch.DefaultEngineSearch;
import entity.PropertiesSearch;
import entity.SearchEnum;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Roman Nagibov
 */
public class ProcessingSearch {

    private PropertiesSearch propertiesSearch;
    private DefaultEngineSearch engine;
    private Boolean stopCommand = false;


    public void setPropertiesEngine(PropertiesSearch propertiesSearch, DefaultEngineSearch engine) {
        this.propertiesSearch = propertiesSearch;
        this.engine = engine;
    }

    public Boolean getStopCommand() {
        return stopCommand;
    }

    public void initStopCommand() {
        this.stopCommand = true;
    }

    public void process() {
        System.out.println("Please, enter your request:");

        this.propertiesSearch.processingSearchMessage();
        this.propertiesSearch.addEngineSearchList(SearchEnum.GOOGLE);
        this.engine.setProp(propertiesSearch);

        List<String> listSearch = engine.search();
        String firstURL = listSearch.get(0);
        String title = engine.processingPage(firstURL, PropertiesSearch.USER_AGENT);

        System.out.println("URL = " + firstURL);
        System.out.println("Title first site = " + title);
    }

    public int getInputUserInt(Scanner sc, String message) {
        int inputNumber;
        while (true) {
            try {
                System.out.println(message);
                inputNumber = sc.nextInt();
                sc.nextLine();

                if (inputNumber > 0)
                    break;

            } catch (InputMismatchException ex) {
                if (sc.hasNextLine())
                    sc.nextLine();
            }
        }

        return inputNumber;
    }

}
