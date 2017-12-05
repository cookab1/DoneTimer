package com.bignerdranch.android.donetimer;

import java.util.ArrayList;

/**
 * Created by cookab1 on 12/5/2017.
 */

public class Job {
    private String mName;
    //private ArrayList<String> mTimeLog;

    public Job(String name) {
        mName = name;
    }

    public void setName(String name) {
        name = mName;
    }

    public String getName() {
        return mName;
    }
}
