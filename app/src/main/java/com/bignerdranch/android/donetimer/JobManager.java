package com.bignerdranch.android.donetimer;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by cookab1 on 12/5/2017.
 */

public class JobManager {
    private static JobManager sJobManager;
    private ArrayList<Job> jobs;

    public static JobManager get(Context context) {
        if (sJobManager == null) {
            sJobManager = new JobManager();
        }
        return sJobManager;
    }
    private JobManager () {
        jobs = new ArrayList<>();
    }
}
