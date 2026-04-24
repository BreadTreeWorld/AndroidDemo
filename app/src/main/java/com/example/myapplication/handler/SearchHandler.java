package com.example.myapplication.handler;


import android.util.Log;

import com.example.myapplication.model.FlightModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class SearchHandler {

    private static final String TAG = "SearchHandler";
    private List<FlightModel> flightList;

    public void initData() {
        flightList = new ArrayList<>();
        flightList.add(new FlightModel("HKG", "PEK", "CX888"));
        flightList.add(new FlightModel("HKG", "JFK", "CX777"));
        flightList.add(new FlightModel("HKG", "SIN", "CX666"));
        flightList.add(new FlightModel("HKG", "CUU", "CX555"));

        Log.d(TAG, "Data init finished，total " + flightList.size() + " records");
    }

    public void printAllFlightInfo() {
        if (flightList == null || flightList.isEmpty()) {
            Log.d(TAG, "The flight list is null");
            return;
        }

        for (FlightModel flight : flightList) {
            Log.d(TAG, flight.toString());
        }
    }

    public int searchFlight() {
        if (flightList != null && !flightList.isEmpty()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonOutput = gson.toJson(flightList);
            Log.d(TAG, "Flight List Data (JSON Format):\n" + jsonOutput);

            System.out.println("Flight List JSON Data:");
            System.out.println(jsonOutput);
        }
        printAllFlightInfo();

        return flightList != null ? flightList.size() : 0;
    }

    public List<FlightModel> getFlightList() {
        return flightList;
    }

}
