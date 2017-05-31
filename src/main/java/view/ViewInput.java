package view;

import engineSearch.DefaultEngineSearch;
import entity.PropertiesSearch;
import service.ProcessingSearch;

import java.util.Scanner;

/**
 * @author Roman Nagibov
 */
public class ViewInput {

    private PropertiesSearch propertiesSearch;
    private DefaultEngineSearch engine;
    private ProcessingSearch processingSearch;


    public ViewInput() {
        propertiesSearch = new PropertiesSearch();
        engine = new DefaultEngineSearch();
        processingSearch = new ProcessingSearch();
    }

    public void view() {
        Scanner sc = new Scanner(System.in);
        while (!(processingSearch.getStopCommand())) {
            int trigger;
            trigger = processingSearch.getInputUserInt(
                    sc,
                    " \nEnter your option : "
                    +" \nPress 1 - start "
                            + "\n 2 - end"
                            + "\n Input number:");

            switch (trigger) {
                case 1: {
                    processingSearch.setPropertiesEngine(propertiesSearch, engine);
                    processingSearch.process();
                    break;
                }
                case 2: {
                    processingSearch.initStopCommand();
                    System.out.println("Stop");
                    break;
                }
                default:
                    System.out.println("Choose exist option:");
                    break;
            }

        }
    }

}
