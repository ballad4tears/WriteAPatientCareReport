package com.example.ro_cot_ta.writeapatientcarereport;

/**
 * Created by Ro-Cot-Ta on 20-May-18.
 */
public class Product {


    private int id;
    private String name;
    private String lastname;
    private double no;
    private String address;
    private double number;
    private String allergic;

    public Product(int id, String name, String lastname, String address, String allergic) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        //this.no = no;
        this.address = address;
        //this.number = number;
        this.allergic = allergic;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public double getNo() {
        return no;
    }

    public String getAddress() {
        return address;
    }


    public double getNumber() {
        return number;
    }

    public String getAllergic() {
        return allergic;
    }
}