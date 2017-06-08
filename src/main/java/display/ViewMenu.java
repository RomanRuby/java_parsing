package display;

import engineSearch.DefaultEngineSearch;
import models.dto.ResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import threadScanners.InstanceScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Nagibov
 */
public class ViewMenu {

    private static final Logger LOGGER = LogManager.getLogger(ViewMenu.class.getName());
    private static ViewMenu viewMenu;
    private InstanceScanner scanner = InstanceScanner.getInstance();
    private DefaultEngineSearch defaultEngineSearch = DefaultEngineSearch.getInstance();
    private boolean run = true;


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
        System.out.println(DisplayParameters.MENU);
        String row = scanner.readRow();
        if (!StringUtils.isNumeric(row)) {
            System.out.println("Input must be number!");
            getModeMenu();
            return;
        }
        int input = Integer.valueOf(row);
        switch (input) {
            case 1: {
                printFirstResult(getResult());
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

    }

    private void stop() {
        System.out.println("Good bye!");
        scanner.stopScannerThread();
        run = false;
    }

    private List<ResponseDto> getResult() {
        List<ResponseDto> result = new ArrayList<>();
        System.out.println("Please, enter your request:");
        String message = scanner.readRow();
        if (!isCheckURLLength(message)) {
            getMainMenu();
            return new ArrayList<>();
        }
        try {
            result.addAll(defaultEngineSearch.search(message));
        } catch (IOException e) {
            LOGGER.info("Info Message Logged", new IOException(e.toString()));
        }
        return result;
    }

    private void printFirstResult(List<ResponseDto> responseDtos) {
        if(responseDtos.size() == 0){
            System.out.println("Empty list");
            return;
        }
        ResponseDto responseDto = responseDtos.get(0);
        String firstURL = responseDto.getURL();
        String title = responseDto.getTitle();

        System.out.println("URL = " + firstURL);
        System.out.println("Title site = " + title);
    }

    private boolean isCheckURLLength(String message) {
        return message.length() < 2048;
    }

}
