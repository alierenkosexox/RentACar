package view;

import business.BookManager;
import business.BrandManager;
import business.CarManager;
import business.ModelManager;
import core.ComboItem;
import core.Helper;
import entity.Book;
import entity.Brand;
import entity.Car;
import entity.Model;
import entity.User;
import entity.Model.Fuel;
import entity.Model.Gear;
import entity.Model.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JPanel pnl_brand;
    private JScrollPane scl_brand;
    private JTable tbl_brand;
    private JPanel pnl_model;
    private JScrollPane scrl_model;
    private JTable tbl_model;
    private JComboBox<ComboItem> cmb_s_model_brand;
    private JComboBox<Model.Gear> cmb_s_model_gear;
    private JComboBox<Model.Fuel> cmb_s_model_fuel;
    private JComboBox<Model.Type> cmb_s_model_type;
    private JButton btn_search_model;
    private JButton btn_cncl_model;
    private JPanel pnl_car;
    private JScrollPane scrl_car;
    private JTable tbl_car;
    private JPanel pnl_booking;
    private JScrollPane scrl_booking;
    private JTable tbl_booking;
    private JPanel pnl_booking_search;
    private JComboBox<Model.Type> cmb_booking_type;
    private JComboBox<Model.Fuel> cmb_booking_fuel;
    private JComboBox<Model.Gear> cmb_booking_gear;
    private JFormattedTextField fld_strt_date;
    private JFormattedTextField fld_fnsh_date;
    private JButton btn_booking_search;
    private JButton btn_cncl_booking;
    private JPanel pnl_book;
    private JScrollPane scrl_book;
    private JTable tbl_book;
    private JFormattedTextField fld_book_strt_date;
    private JFormattedTextField fld_book_fnsh_date;
    private JComboBox<ComboItem> cmb_book_car;
    private JButton btn_book_search;
    private JButton btn_cncl_book;
    private JPanel pnl_book_search;
    private User user;
    private DefaultTableModel tmdl_brand;
    private DefaultTableModel tmdl_model;
    private DefaultTableModel tmdl_car;
    private DefaultTableModel tmdl_booking;
    private DefaultTableModel tmdl_book;
    private BrandManager brandManager;
    private ModelManager modelManager;
    private BookManager bookManager;
    private CarManager carManager;
    private JPopupMenu brand_menu;
    private JPopupMenu model_menu;
    private JPopupMenu car_menu;
    private JPopupMenu booking_menu;
    private JPopupMenu book_menu;
    private Object[] col_model;
    private Object[] col_car;
    private Object[] col_book;

    public AdminView(User user) {
        //this.$$$setupUI$$$();
        this.tmdl_brand = new DefaultTableModel();
        this.tmdl_model = new DefaultTableModel();
        this.tmdl_car = new DefaultTableModel();
        this.tmdl_booking = new DefaultTableModel();
        this.tmdl_book = new DefaultTableModel();
        this.brandManager = new BrandManager();
        this.modelManager = new ModelManager();
        this.carManager = new CarManager();
        this.bookManager = new BookManager();
        this.add(this.container);
        this.guiInitilaze(1000, 500);
        this.user = user;
        if (this.user == null) {
            this.dispose();
        }

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername());
        this.loadComponent();
        this.loadBrandTable();
        this.loadBrandComponent();
        this.loadModelTable((ArrayList)null);
        this.loadModelComponent();
        this.loadModelFilter();
        this.loadCarTable();
        this.loadCarComponent();
        this.loadBookingTable((ArrayList)null);
        this.loadBookingComponent();
        this.loadBookingFilter();
        this.loadBookTable((ArrayList)null);
        this.loadBookComponent();
        this.loadBookFilterCar();
    }

    private void loadComponent() {
        this.btn_logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminView.this.dispose();
                new LoginView();
            }
        });
    }

    private void loadBookComponent() {
        this.tableRowSelect(this.tbl_book);
        this.book_menu = new JPopupMenu();
        this.book_menu.add("İptal Et").addActionListener((e) -> {
            if (Helper.confirm("sure")) {
                int selectBookId = this.getTableSelectedRow(this.tbl_book, 0);
                if (this.bookManager.delete(selectBookId)) {
                    Helper.showMsg("done");
                    this.loadBookTable((ArrayList)null);
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        this.tbl_book.setComponentPopupMenu(this.book_menu);
        this.btn_book_search.addActionListener((e) -> {
            ComboItem selectedCar = (ComboItem)this.cmb_book_car.getSelectedItem();
            int carId = 0;
            if (selectedCar != null) {
                carId = selectedCar.getKey();
            }

            ArrayList<Book> bookListBySearch = this.bookManager.searchForTable(carId);
            ArrayList<Object[]> bookRowListBySearch = this.bookManager.getForTable(this.col_book.length, bookListBySearch);
            this.loadBookTable(bookRowListBySearch);
        });
        this.btn_cncl_book.addActionListener((e) -> {
            this.loadBookFilterCar();
        });
    }

    private void loadBookTable(ArrayList<Object[]> bookList) {
        this.col_book = new Object[]{"ID", "Plaka", "Araç Marka", "Araç Model", "Müşteri", "Telefon", "Mail", "T.C.", "Başlangıç Tarihi", "Bitiş Tarihi", "Fiyat"};
        if (bookList == null) {
            bookList = this.bookManager.getForTable(this.col_book.length, this.bookManager.findAll());
        }

        this.createTable(this.tmdl_book, this.tbl_book, this.col_book, bookList);
    }

    public void loadBookFilterCar() {
        this.cmb_book_car.removeAllItems();
        Iterator var1 = this.carManager.findAll().iterator();

        while(var1.hasNext()) {
            Car obj = (Car)var1.next();
            this.cmb_book_car.addItem(new ComboItem(obj.getId(), obj.getPlate()));
        }

        this.cmb_book_car.setSelectedItem((Object)null);
    }

    private void loadBookingComponent() {
        this.tableRowSelect(this.tbl_booking);
        this.booking_menu = new JPopupMenu();
        this.booking_menu.add("Rezervasyon Yap").addActionListener((e) -> {
            int selectCarId = this.getTableSelectedRow(this.tbl_booking, 0);
            BookingView bookingView = new BookingView(this.carManager.getById(selectCarId), this.fld_strt_date.getText(), this.fld_fnsh_date.getText());
            bookingView.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    AdminView.this.loadBookingTable((ArrayList)null);
                    AdminView.this.loadBookingFilter();
                    AdminView.this.loadBookTable((ArrayList)null);
                }
            });
        });
        this.tbl_booking.setComponentPopupMenu(this.booking_menu);
        this.btn_booking_search.addActionListener((e) -> {
            ArrayList<Car> carList = this.carManager.searchForBooking(this.fld_strt_date.getText(), this.fld_fnsh_date.getText(), (Model.Type)this.cmb_booking_type.getSelectedItem(), (Model.Gear)this.cmb_booking_gear.getSelectedItem(), (Model.Fuel)this.cmb_booking_fuel.getSelectedItem());
            ArrayList<Object[]> carBookingRow = this.carManager.getForTable(this.col_car.length, carList);
            this.loadBookingTable(carBookingRow);
        });
        this.btn_cncl_booking.addActionListener((e) -> {
            this.loadBookingFilter();
        });
    }

    private void loadBookingTable(ArrayList<Object[]> carList) {
        Object[] col_booking_list = new Object[]{"ID", "Marka", "Model", "Plaka", "Renk", "KM", "Yıl", "Tip", "Yakıt Türü", "Vites"};
        this.createTable(this.tmdl_booking, this.tbl_booking, col_booking_list, carList);
    }

    public void loadBookingFilter() {
        this.cmb_booking_type.setModel(new DefaultComboBoxModel(Type.values()));
        this.cmb_booking_type.setSelectedItem((Object)null);
        this.cmb_booking_gear.setModel(new DefaultComboBoxModel(Gear.values()));
        this.cmb_booking_gear.setSelectedItem((Object)null);
        this.cmb_booking_fuel.setModel(new DefaultComboBoxModel(Fuel.values()));
        this.cmb_booking_fuel.setSelectedItem((Object)null);
    }

    private void loadCarComponent() {
        this.tableRowSelect(this.tbl_car);
        this.car_menu = new JPopupMenu();
        this.car_menu.add("Yeni").addActionListener((e) -> {
            CarView carView = new CarView(new Car());
            carView.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    AdminView.this.loadCarTable();
                }
            });
        });
        this.car_menu.add("Güncelle").addActionListener((e) -> {
            int selectModelId = this.getTableSelectedRow(this.tbl_car, 0);
            CarView carView = new CarView(this.carManager.getById(selectModelId));
            carView.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    AdminView.this.loadCarTable();
                }
            });
        });
        this.car_menu.add("Sil").addActionListener((e) -> {
            if (Helper.confirm("sure")) {
                int selectCarId = this.getTableSelectedRow(this.tbl_car, 0);
                if (this.carManager.delete(selectCarId)) {
                    Helper.showMsg("done");
                    this.loadCarTable();
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        this.tbl_car.setComponentPopupMenu(this.car_menu);
    }

    public void loadCarTable() {
        this.col_car = new Object[]{"ID", "Marka", "Model", "Plaka", "Renk", "KM", "Yıl", "Tip", "Yakıt Türü", "Vites"};
        ArrayList<Object[]> carList = this.carManager.getForTable(this.col_car.length, this.carManager.findAll());
        this.createTable(this.tmdl_car, this.tbl_car, this.col_car, carList);
    }

    private void loadModelComponent() {
        this.tableRowSelect(this.tbl_model);
        this.model_menu = new JPopupMenu();
        this.model_menu.add("Yeni").addActionListener((e) -> {
            ModelView modelView = new ModelView(new Model());
            modelView.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    AdminView.this.loadModelTable((ArrayList)null);
                }
            });
        });
        this.model_menu.add("Güncelle").addActionListener((e) -> {
            int selectModelId = this.getTableSelectedRow(this.tbl_model, 0);
            ModelView modelView = new ModelView(this.modelManager.getById(selectModelId));
            modelView.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    AdminView.this.loadModelTable((ArrayList)null);
                    AdminView.this.loadCarTable();
                    AdminView.this.loadBookTable((ArrayList)null);
                }
            });
        });
        this.model_menu.add("Sil").addActionListener((e) -> {
            if (Helper.confirm("sure")) {
                int selectModelId = this.getTableSelectedRow(this.tbl_model, 0);
                if (this.modelManager.delete(selectModelId)) {
                    Helper.showMsg("done");
                    this.loadModelTable((ArrayList)null);
                    this.loadCarTable();
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        this.tbl_model.setComponentPopupMenu(this.model_menu);
        this.btn_search_model.addActionListener((e) -> {
            ComboItem selectedBrand = (ComboItem)this.cmb_s_model_brand.getSelectedItem();
            int brandId = 0;
            if (selectedBrand != null) {
                brandId = selectedBrand.getKey();
            }

            ArrayList<Model> modelListBySearch = this.modelManager.searchForTable(brandId, (Model.Fuel)this.cmb_s_model_fuel.getSelectedItem(), (Model.Gear)this.cmb_s_model_gear.getSelectedItem(), (Model.Type)this.cmb_s_model_type.getSelectedItem());
            ArrayList<Object[]> modelRowListBySearch = this.modelManager.getForTable(this.col_model.length, modelListBySearch);
            this.loadModelTable(modelRowListBySearch);
        });
        this.btn_cncl_model.addActionListener((e) -> {
            this.cmb_s_model_type.setSelectedItem((Object)null);
            this.cmb_s_model_gear.setSelectedItem((Object)null);
            this.cmb_s_model_fuel.setSelectedItem((Object)null);
            this.cmb_s_model_brand.setSelectedItem((Object)null);
            this.loadModelTable((ArrayList)null);
        });
    }

    public void loadModelTable(ArrayList<Object[]> modelList) {
        this.col_model = new Object[]{"Model ID", "Marka", "Model Adı", "Tip", "Yıl", "Yakıt Türü", "Vites"};
        if (modelList == null) {
            modelList = this.modelManager.getForTable(this.col_model.length, this.modelManager.findAll());
        }

        this.createTable(this.tmdl_model, this.tbl_model, this.col_model, modelList);
    }

    public void loadModelFilter() {
        this.cmb_s_model_type.setModel(new DefaultComboBoxModel(Type.values()));
        this.cmb_s_model_type.setSelectedItem((Object)null);
        this.cmb_s_model_gear.setModel(new DefaultComboBoxModel(Gear.values()));
        this.cmb_s_model_gear.setSelectedItem((Object)null);
        this.cmb_s_model_fuel.setModel(new DefaultComboBoxModel(Fuel.values()));
        this.cmb_s_model_fuel.setSelectedItem((Object)null);
        this.loadModelFilterBrand();
    }

    public void loadModelFilterBrand() {
        this.cmb_s_model_brand.removeAllItems();
        Iterator var1 = this.brandManager.findAll().iterator();

        while(var1.hasNext()) {
            Brand obj = (Brand)var1.next();
            this.cmb_s_model_brand.addItem(new ComboItem(obj.getId(), obj.getName()));
        }

        this.cmb_s_model_brand.setSelectedItem((Object)null);
    }

    public void loadBrandComponent() {
        this.tableRowSelect(this.tbl_brand);
        this.brand_menu = new JPopupMenu();
        this.brand_menu.add("Yeni").addActionListener((e) -> {
            BrandView brandView = new BrandView((Brand)null);
            brandView.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    AdminView.this.loadBrandTable();
                    AdminView.this.loadModelTable((ArrayList)null);
                    AdminView.this.loadModelFilterBrand();
                }
            });
        });
        this.brand_menu.add("Güncelle").addActionListener((e) -> {
            int selectBrandId = this.getTableSelectedRow(this.tbl_brand, 0);
            BrandView brandView = new BrandView(this.brandManager.getById(selectBrandId));
            brandView.addWindowListener(new WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    AdminView.this.loadBrandTable();
                    AdminView.this.loadModelTable((ArrayList)null);
                    AdminView.this.loadModelFilterBrand();
                    AdminView.this.loadCarTable();
                    AdminView.this.loadBookTable((ArrayList)null);
                }
            });
        });
        this.brand_menu.add("Sil").addActionListener((e) -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(this.tbl_brand, 0);
                if (this.brandManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    this.loadBrandTable();
                    this.loadModelTable((ArrayList)null);
                    this.loadModelFilterBrand();
                    this.loadCarTable();
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        this.tbl_brand.setComponentPopupMenu(this.brand_menu);
    }

    public void loadBrandTable() {
        Object[] col_brand = new Object[]{"Marka ID", "Marka Adı"};
        ArrayList<Object[]> brandList = this.brandManager.getForTable(col_brand.length);
        this.createTable(this.tmdl_brand, this.tbl_brand, col_brand, brandList);
    }

    private void createUIComponents() throws ParseException {
        this.fld_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_strt_date.setText("10/10/2023");
        this.fld_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_fnsh_date.setText("16/10/2023");
        this.fld_book_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_book_strt_date.setText("10/10/2023");
        this.fld_book_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_book_fnsh_date.setText("16/10/2023");
    }
}
