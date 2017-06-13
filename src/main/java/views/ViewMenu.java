package views;

import models.EnumSearch;
import models.dto.ResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.LinksSearch;
import threadScanners.InstanceScanner;

import java.io.IOException;

/**
 * @author Roman Nagibov
 */
public class ViewMenu {

    private static final Logger LOGGER = LogManager.getLogger(ViewMenu.class);
    private static ViewMenu viewMenu;

    private InstanceScanner scanner = InstanceScanner.getInstance();
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
                executeResultMenu();
            }
        }

    }

    private void processRequest() {
        System.out.println("Please, enter your request:");
        String message = scanner.readRow();
        if (isCorrectMessage(message)) {
            ResponseDto result = getResult(message);
            printResult(result);
        }
    }

    private void stop() {
        System.out.println("Good bye!");
        scanner.stopScannerThread();
        run = false;
    }

    private ResponseDto getResult(String message) {
        ResponseDto result = null;
        try {
            result = processMessage(message);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return result;
    }

    private ResponseDto processMessage(String message) throws IOException {
        EnumSearch searchEngine = EnumSearch.GOOGLE;
        LinksSearch linksSearch = searchEngine.getSearchEngine(message);
        return linksSearch.search();
    }

    private void printResult(ResponseDto responseDto) {
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
