package entity;

import core.ComboItem;

public class Model {
    private int id;
    private int brand_id;
    private String name;
    private String year;
    private Type type;
    private Fuel fuel;
    private Gear gear;
    private Brand brand;

    public Model() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrand_id() {
        return this.brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Fuel getFuel() {
        return this.fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Gear getGear() {
        return this.gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ComboItem getComboItem() {
        int var10002 = this.getId();
        String var10003 = this.getBrand().getName();
        return new ComboItem(var10002, var10003 + " - " + this.getName() + " - " + this.getYear() + " - " + this.getGear());
    }

    public String toString() {
        return "Model{id=" + this.id + ", brand_id=" + this.brand_id + ", name='" + this.name + "', type=" + this.type + ", year='" + this.year + "', fuel=" + this.fuel + ", gear=" + this.gear + ", brand=" + this.brand + "}";
    }

    public static enum Type {
        SEDAN,
        HACBACK;

        private Type() {
        }
    }

    public static enum Fuel {
        GASOLINE,
        LPG,
        ELECTRIC,
        DIESEL;

        private Fuel() {
        }
    }

    public static enum Gear {
        MANUEL,
        AUTO;

        private Gear() {
        }
    }
}
