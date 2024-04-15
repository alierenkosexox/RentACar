package view;

import business.BrandManager;
import core.Helper;
import entity.Brand;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BrandView extends Layout {
    private JPanel container;
    private JLabel lbl_brand;
    private JLabel lbl_brand_name;
    private JTextField fld_brand_name;
    private JButton btn_brand_save;
    private Brand brand;
    private BrandManager brandManager;

    public BrandView(Brand brand) {
        //this.$$$setupUI$$$();
        this.brandManager = new BrandManager();
        this.brand = brand;
        this.add(this.container);
        this.guiInitilaze(300, 200);
        if (brand != null) {
            this.fld_brand_name.setText(brand.getName());
        }

        this.btn_brand_save.addActionListener((e) -> {
            if (Helper.isFieldEmpty(this.fld_brand_name)) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                if (this.brand == null) {
                    Brand obj = new Brand(this.fld_brand_name.getText());
                    result = this.brandManager.save(obj);
                } else {
                    this.brand.setName(this.fld_brand_name.getText());
                    result = this.brandManager.update(this.brand);
                }

                if (result) {
                    Helper.showMsg("done");
                    this.dispose();
                } else {
                    Helper.showMsg("error");
                }
            }

        });
    }
}
