package entity;

import java.time.LocalDate;

public class Book {
    private int id;
    private int car_id;
    private Car car;
    private String name;
    private String idno;
    private String mpno;
    private String mail;
    private LocalDate strt_date;
    private LocalDate fnsh_date;
    private String bCase;
    private String note;
    private int prc;

    public Book() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCar_id() {
        return this.car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdno() {
        return this.idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getMpno() {
        return this.mpno;
    }

    public void setMpno(String mpno) {
        this.mpno = mpno;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getStrt_date() {
        return this.strt_date;
    }

    public void setStrt_date(LocalDate strt_date) {
        this.strt_date = strt_date;
    }

    public LocalDate getFnsh_date() {
        return this.fnsh_date;
    }

    public void setFnsh_date(LocalDate fnsh_date) {
        this.fnsh_date = fnsh_date;
    }

    public String getbCase() {
        return this.bCase;
    }

    public void setbCase(String bCase) {
        this.bCase = bCase;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getPrc() {
        return this.prc;
    }

    public void setPrc(int prc) {
        this.prc = prc;
    }

    public String toString() {
        return "Book{id=" + this.id +
                ", car_id=" + this.car_id +
                ", car=" + this.car +
                ", name='" + this.name + "" +
                "', idno='" + this.idno + "', mpno='" + this.mpno + "', mail='" + this.mail +
                "', strt_date=" + this.strt_date + ", fnsh_date=" + this.fnsh_date +
                ", bCase='" + this.bCase + "', note='" + this.note + "', prc=" + this.prc + "}";
    }
}

