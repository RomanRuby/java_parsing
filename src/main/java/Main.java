import display.DisplayParameters;
import display.ViewMenu;

/**
 * Created by Roman Nagibov
 */
public class Main {

    private static ViewMenu menu = ViewMenu.getInstance();

    public static void main(String[] args) {
        System.out.println(DisplayParameters.GREETING);
        menu.executeMainMenu();
    }

}
