package entity;

public class Car {
    private int id;
    private int model_id;
    private Color color;
    private int km;
    private String plate;
    private Model model;
    private Brand brand;

    public Car() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModel_id() {
        return this.model_id;
    }

    public void setModel_id(int model_id) {
        this.model_id = model_id;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getKm() {
        return this.km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Model getModel() {
        return this.model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String toString() {
        return "Car{id=" + this.id + ", model_id=" + this.model_id + ", color=" + this.color + ", km=" + this.km + ", plate='" + this.plate + "', model=" + this.model + ", brand=" + this.brand + "}";
    }

    public static enum Color {
        RED,
        BLUE,
        WHITE,
        GREEN,
        AQUA;

        private Color() {
        }
    }
}