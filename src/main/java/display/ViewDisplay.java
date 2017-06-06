package display;

import engineSearch.DefaultEngineSearch;
import models.PropertiesSearch;
import models.SearchEnum;
import models.dto.QueryDto;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Roman Nagibov
 */
public class ViewDisplay {

    private PropertiesSearch propertiesSearch;
    private DefaultEngineSearch engine;
    private Boolean stopCommand = false;

    public ViewDisplay() {
        propertiesSearch = new PropertiesSearch();
        engine = new DefaultEngineSearch();
    }

    public void demonstrate() {
        Scanner scanner = new Scanner(System.in);
        while (!(getStopCommand())) {
            int trigger;
            trigger = getInputUserInt(
                    scanner,
                    " \nEnter your option : "
                            + " \nPress 1 - start "
                            + "\n 2 - end"
                            + "\n Input number:");

            switch (trigger) {
                case 1: {
                    mainRealization();
                    break;
                }
                case 2: {
                    initStopCommand();
                    System.out.println("Stop");
                    break;
                }
                default:
                    System.out.println("Choose exist option:");
                    break;
            }

        }
    }

    private void mainRealization() {
        System.out.println("Please, enter your request:");

        propertiesSearch.processingSearchMessage();
        propertiesSearch.addEngineSearchList(SearchEnum.GOOGLE);
        engine.setPropertiesSearch(propertiesSearch);

        List<QueryDto> listSearch = engine.search();
        outputFirstResult(listSearch.get(0));
    }

    private Boolean getStopCommand() {
        return stopCommand;
    }

    private void initStopCommand() {
        this.stopCommand = true;
    }

    private int getInputUserInt(Scanner sc, String message) {
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

    private void outputFirstResult(QueryDto firstQueryDto){
        String firstURL = firstQueryDto.getQueryUri();
        String title = firstQueryDto.getTitle();

        System.out.println("URL = " + firstURL);
        System.out.println("Title first site = " + title);
    }
}
