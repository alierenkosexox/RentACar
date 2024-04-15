package view;

import business.UserManager;
import core.Helper;
import entity.User;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JPasswordField fld_pass;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_pass;
    private final UserManager userManager;

    public LoginView() {
        //this.$$$setupUI$$$();
        this.userManager = new UserManager();
        this.add(this.container);
        this.guiInitilaze(400, 400);
        this.w_bottom.setBackground(Color.DARK_GRAY);
        this.container.setBackground(Color.MAGENTA);
        this.btn_login.addActionListener((e) -> {
            JTextField[] checkFieldList = new JTextField[]{this.fld_username, this.fld_pass};
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(), this.fld_pass.getText());
                if (loginUser == null) {
                    Helper.showMsg("notFound");
                } else {
                    new AdminView(loginUser);
                    this.dispose();
                }
            }

        });
    }
}
