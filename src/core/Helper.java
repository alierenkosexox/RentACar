package core;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Helper {
    public Helper() {
    }

    public static void setTheme() {
        optionPaneTR();
        UIManager.LookAndFeelInfo[] var0 = UIManager.getInstalledLookAndFeels();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            UIManager.LookAndFeelInfo info = var0[var2];
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception var5) {
                    System.out.println(var5.getMessage());
                }
                break;
            }
        }

    }

    public static void showMsg(String str) {
        String msg;
        String title;
        switch (str) {
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz !";
                title = "Hata!";
                break;
            case "done":
                msg = "İşlem Başarılı !";
                title = "Sonuç";
                break;
            case "notFound":
                msg = "Kayıt bulunamadı !";
                title = "Bulunamadı";
                break;
            case "error":
                msg = "Hatalı işlem yaptınız !";
                title = "Hata!";
                break;
            default:
                msg = str;
                title = "Mesaj";
        }

        JOptionPane.showMessageDialog((Component)null, msg, title, 1);
    }

    public static boolean confirm(String str) {
        String msg;
        if (str.equals("sure")) {
            msg = "Bu işlemi yapmak istediğine emin misin ?";
        } else {
            msg = str;
        }

        return JOptionPane.showConfirmDialog((Component)null, msg, "Emin misin ?", 0) == 0;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        JTextField[] var1 = fieldList;
        int var2 = fieldList.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            JTextField field = var1[var3];
            if (isFieldEmpty(field)) {
                return true;
            }
        }

        return false;
    }

    public static int getLocationPoint(String type, Dimension size) {
        int var10000;
        switch (type) {
            case "x":
                var10000 = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                var10000 = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                var10000 = 0;
        }

        return var10000;
    }

    public static void optionPaneTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }
}