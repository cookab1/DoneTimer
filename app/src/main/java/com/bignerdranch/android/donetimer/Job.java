package com.bignerdranch.android.donetimer;

import java.util.UUID;

/**
 * Created by cookab1 on 12/5/2017.
 */

public class Job {
    private UUID mID;
    private String mName;
    private boolean finished;
    //private ArrayList<String> mTimeLog;

    public Job() {
        this(UUID.randomUUID());
    }

    public Job(UUID id) {
        mID = id;
        finished = false;
    }

    public UUID getId() {
        return mID;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public boolean isFinished() {
        return finished;
    }
}
