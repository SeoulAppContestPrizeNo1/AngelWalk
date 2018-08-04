package com.walk.angel.angelwalk.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WheelchairChargingData implements Serializable {

    private int mId;

    private String mAddress;

    public WheelchairChargingData(int id, String address) {
        mId = id;
        mAddress = address;
    }

    public int getId() {
        return mId;
    }

    public String getAddress() {
        return mAddress;
    }

    private static int lastContactId = 0;

    public static List<WheelchairChargingData> createWheelchairChargingList(int numCharging) {
        List<WheelchairChargingData> chargings = new ArrayList<>();

        for (int i = 1; i <= numCharging; i++) {
            chargings.add(new WheelchairChargingData(i,"주소 " + i));
        }

        return chargings;
    }
}
