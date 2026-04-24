package com.example.myapplication.model;

public class FlightModel {
    private String departure;
    private String arrival;
    private String flightNo;

    public FlightModel(String departure, String arrival, String flightNo) {
        this.departure = departure;
        this.arrival = arrival;
        this.flightNo = flightNo;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    @Override
    public String toString() {
        return "FlightModel{" +
                "departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", flightNo='" + flightNo + '\'' +
                '}';
    }

}
