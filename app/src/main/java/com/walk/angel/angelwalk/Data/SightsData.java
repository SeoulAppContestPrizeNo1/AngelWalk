package com.walk.angel.angelwalk.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SightsData implements Serializable {

    private int mId;

    private String mName;
    private String mAddress;

    public SightsData(int id, String name, String address) {
        mId = id;
        mName = name;
        mAddress = address;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    private static int lastContactId = 0;

    public static List<SightsData> createSightsList(int numContacts) {
        List<SightsData> sights = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            sights.add(new SightsData(i,"관광지 " + i, "주소 " + i));
        }

        sights.add(new SightsData(numContacts++,"아름", "테스트"));
        return sights;
    }
}
