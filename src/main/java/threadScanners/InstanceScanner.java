package threadScanners;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author Roman Nagibov
 */
@Data
public class InstanceScanner {

    private static final Logger LOGGER = LogManager.getLogger(InstanceScanner.class);
    private static InstanceScanner instance;
    private Scanner scanner = new Scanner(System.in);
    private ExecutorService executor = Executors.newFixedThreadPool(1);


    private InstanceScanner() {
    }

    public static InstanceScanner getInstance() {
        if (instance == null) {
            instance = new InstanceScanner();
        }
        return instance;
    }

    public String readRow() {
        Future<String> future = executor.submit(processingSearchMessage);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.info(e.getMessage());
        }
        return null;
    }

    public void stopScannerThread() {
        executor.shutdown();
    }

    private Callable<String> processingSearchMessage = () -> scanner.nextLine();

}
