package com.matoso.entities;

public class Bank
{
    private int id;
    private String firm;

    public Bank() {}
    public Bank(int id, String firm)
    {
        this.firm = firm;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirm() {
        return firm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }
}
