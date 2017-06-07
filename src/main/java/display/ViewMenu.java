package display;

import engineSearch.DefaultEngineSearch;
import models.dto.ResponseDto;
import service.AbstractSearchLinks;
import threadScanners.InstanceScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * @author Roman Nagibov
 */
public class ViewMenu {


    private boolean run = true;
    private static ViewMenu viewMenu;
    private InstanceScanner scanner = InstanceScanner.getInstance();

    private ViewMenu() {
    }


    public static ViewMenu getInstance() {
        if (null == viewMenu) {
            viewMenu = new ViewMenu();
        }
        return viewMenu;
    }

    public void getMainMenu() {
        while (run) {
            getModeMenu();
        }
    }

    private void getModeMenu() {
        try {
            System.out.println(AbstractSearchLinks.MENU);
            int input = Integer.valueOf(scanner.readRow());
            switch (input) {
                case 1: {
                    printFirstResult(getResult().get(0));
                    break;
                }
                case 2: {
                    stop();
                    break;
                }
                default:
                    System.out.println("Choose right option!");
                    getMainMenu();
            }
        } catch (InputMismatchException ex) {
            System.out.println("Input must be number!");
            getModeMenu();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            getModeMenu();
        }
    }

    private void stop() {
        System.out.println("Good bye!");
        scanner.stopScannerThread();
        run = false;
    }


    private List<ResponseDto> getResult() {
        List<ResponseDto> result = new ArrayList<>();
        DefaultEngineSearch defaultEngineSearch = DefaultEngineSearch.getInstance();
        System.out.println("Please, enter your request:");
        try {
            result.addAll(defaultEngineSearch.search((scanner.readRow())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void printFirstResult(List<ResponseDto> queryDtos) {
        Collections
        String firstURL = queryDto.getQueryUri();
        String title = queryDto.getTitle();

        System.out.println("URL = " + firstURL);
        System.out.println("Title first site = " + title);
    }

}
