package com.change.domain;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/16
 * Time: 下午5:10
 */
public class Location {

    private String place;
    private int year;

    public Location(String place, int year) {
        this.place = place;
        this.year = year;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Location{" +
                "place='" + place + '\'' +
                ", year=" + year +
                '}';
    }
}
