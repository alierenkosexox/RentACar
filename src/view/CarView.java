package view;

import business.CarManager;
import business.ModelManager;
import core.ComboItem;
import core.Helper;
import entity.Car;
import entity.Model;
import entity.Car.Color;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CarView extends Layout {
    private JPanel container;
    private JComboBox<ComboItem> cmb_model;
    private JComboBox<Car.Color> cmb_color;
    private JTextField fld_km;
    private JTextField fld_plate;
    private JButton btn_car_save;
    private Car car;
    private CarManager carManager;
    private ModelManager modelManager;

    public CarView(Car car) {
        this.car = car;
        //this.$$$setupUI$$$();
        this.carManager = new CarManager();
        this.modelManager = new ModelManager();
        this.add(this.container);
        this.guiInitilaze(300, 400);
        this.cmb_color.setModel(new DefaultComboBoxModel(Color.values()));
        Iterator var2 = this.modelManager.findAll().iterator();

        while(var2.hasNext()) {
            Model model = (Model)var2.next();
            this.cmb_model.addItem(model.getComboItem());
        }

        if (this.car.getId() != 0) {
            ComboItem selectedItem = car.getModel().getComboItem();
            this.cmb_model.getModel().setSelectedItem(selectedItem);
            this.cmb_color.getModel().setSelectedItem(car.getColor());
            this.fld_plate.setText(car.getPlate());
            this.fld_km.setText(Integer.toString(car.getKm()));
        }

        this.btn_car_save.addActionListener((e) -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_km, this.fld_plate})) {
                Helper.showMsg("fill");
            } else {
                ComboItem selectedModel = (ComboItem)this.cmb_model.getSelectedItem();
                this.car.setModel_id(selectedModel.getKey());
                this.car.setColor((Car.Color)this.cmb_color.getSelectedItem());
                this.car.setPlate(this.fld_plate.getText());
                this.car.setKm(Integer.parseInt(this.fld_km.getText()));
                boolean result;
                if (this.car.getId() != 0) {
                    result = this.carManager.update(this.car);
                } else {
                    result = this.carManager.save(this.car);
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