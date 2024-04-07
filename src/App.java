import core.Db;
import view.LoginView;

import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) {

        Connection con = Db.getInstance();
        LoginView loginView = new LoginView();

    }
}