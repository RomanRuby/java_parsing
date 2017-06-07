package threadScanners;

import lombok.Data;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author Roman Nagibov
 */
@Data
public class InstanceScanner {

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
            e.printStackTrace();
        }
        return null;
    }

    public void stopScannerThread() {
        executor.shutdown();
    }

    private Callable<String> processingSearchMessage = () -> scanner.nextLine();

}
