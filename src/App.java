import core.Helper;
import view.LoginView;

public class App {
    public App() {
    }

    public static void main(String[] args) {
        Helper.setTheme();
        new LoginView();
    }
}
