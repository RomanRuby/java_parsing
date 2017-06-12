package views;

import engineSearch.DefaultEngineSearch;
import models.dto.ResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import threadScanners.InstanceScanner;

import java.io.IOException;

/**
 * @author Roman Nagibov
 */
public class ViewMenu {

    private static final Logger LOGGER = LogManager.getLogger(ViewMenu.class);
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
                processRequest();
                break;
            }
            case "2": {
                stop();
                break;
            }
            default: {
                System.out.println("Choose right option!");
                executeMainMenu();
            }
        }

    }

    private void processRequest() {
        String message = getMessage();
        if (isCorrectMessage(message)) {
            ResponseDto result = getResultList(message);
            printFirstResult(result);
        }
    }

    private void stop() {
        System.out.println("Good bye!");
        scanner.stopScannerThread();
        run = false;
    }

    private String getMessage() {
        System.out.println("Please, enter your request:");
        return scanner.readRow();
    }

    private ResponseDto getResultList(String message) {
        ResponseDto result = null;
        try {
            result = (defaultEngineSearch.search(message));
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return result;
    }

    private void printFirstResult(ResponseDto responseDto) {
        System.out.println("URL = " + responseDto.getURL());
        System.out.println("Title site = " + responseDto.getTitle());
    }

    private boolean isCorrectMessage(String message) {
        if (!(message.length() < 2048)) {
            System.out.println("Query longer than 2048");
            executeResultMenu();
            return false;
        }
        return true;
    }

}
