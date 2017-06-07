import display.ViewMenu;
import service.AbstractSearchLinks;

/**
 * Created by Roman Nagibov
 */
public class Main {

    private static ViewMenu menu = ViewMenu.getInstance();


    public static void main(String[] args) {
        System.out.println(AbstractSearchLinks.GREETING);
        menu.getMainMenu();
    }

}
