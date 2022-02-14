package com.example.parvatiarchana.table;


import java.util.Objects;

public class Holidays {

    private  String name;
    private  String date;

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Holidays(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holidays holidays = (Holidays) o;
        return Objects.equals(name, holidays.name) && Objects.equals(date, holidays.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date);
    }
}