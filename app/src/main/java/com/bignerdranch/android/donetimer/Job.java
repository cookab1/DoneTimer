package com.bignerdranch.android.donetimer;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by cookab1 on 12/5/2017.
 */

public class Job {
    private UUID mID;
    private String mName;
    private boolean finished;
    private boolean started;
    private ArrayList<String> mLog;

    public Job() {
        this(UUID.randomUUID());
    }

    public Job(UUID id) {
        mID = id;
        finished = false;
        started = false;
        mLog = new ArrayList<String>();
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

    public void finished() {
        finished = true;
    }

    public void notFinished() {
        finished = false;
    }

    public boolean isStarted() {
        return started;
    }

    public void started() {
        started = true;
    }

    public ArrayList<String> getLog() {
        return mLog;
    }
}
