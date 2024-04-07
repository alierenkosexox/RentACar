package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static void showMsg(String str) {

        String msg;
        String title;

        switch (str) {
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz !";
                title = "Hata!";
            }
            case "done" -> {
                msg = "İşlem Başarılı !";
                title = "Sonuç";
            }
            case "notFound" -> {
                msg = "Kayıt bulunamadı !";
                title = "Bulunamadı";
            }
            case "error" -> {
                msg = "Hatalı işlem yaptınız !";
                title = "Hata!";
            }
            default -> {
                msg = str;
                title = "Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean isFieldListEmpty(JTextField[] fieldList){
        for (JTextField field : fieldList){
            if (isFieldEmpty(field))
                return true;
        }
        return false;
    }
}
