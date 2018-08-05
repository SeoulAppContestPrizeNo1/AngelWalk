package com.walk.angel.angelwalk.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SightsData implements Serializable {

    private int mId;

    private String mName;
    private String mAddress;
    private String mCategory;

    public SightsData(int id, String name, String address, String category) {
        mId = id;
        mName = name;
        mAddress = address;
        mCategory = category;
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

    public String getCategory() {
        return mCategory;
    }

    private static int lastContactId = 0;

    public static List<SightsData> createSightsList(int numContacts) {
        List<SightsData> sights = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            sights.add(new SightsData(i,"관광지 " + i, "주소 " + i, "고궁"));
        }

        sights.add(new SightsData(numContacts++,"아름", "테스트","공원"));
        return sights;
    }
}
