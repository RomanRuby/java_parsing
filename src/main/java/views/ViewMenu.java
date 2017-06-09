package views;

import engineSearch.DefaultEngineSearch;
import models.dto.ResponseDto;
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

    public void executeMainMenu() {
        while (run) {
            executeResultMenu();
        }
    }

    private void executeResultMenu() {
        System.out.println(ViewParameters.MENU);
        switch (scanner.readRow()) {
            case "1": {
                printFirstResult(getResult());
                break;
            }
            case "2": {
                stop();
                break;
            }
            default:
                System.out.println("Choose right option!");
                executeMainMenu();
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
            System.out.println("Query longer than 2048");
            executeMainMenu();
            return null;
        }
        try {
            result.addAll(defaultEngineSearch.search(message));
        } catch (IOException e) {
            LOGGER.info("Info Message Logged", e.getMessage());
        }
        return result;
    }

    private void printFirstResult(List<ResponseDto> responseDtos) {
        if(null == responseDtos){
            return;
        }
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
