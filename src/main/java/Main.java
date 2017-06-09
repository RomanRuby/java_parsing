import views.ViewParameters;
import views.ViewMenu;

/**
 * Created by Roman Nagibov
 */
public class Main {

    private static ViewMenu menu = ViewMenu.getInstance();

    public static void main(String[] args) {
        System.out.println(ViewParameters.GREETING);
        menu.executeMainMenu();
    }

}
