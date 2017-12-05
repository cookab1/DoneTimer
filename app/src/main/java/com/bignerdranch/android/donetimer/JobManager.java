package com.bignerdranch.android.donetimer;

import android.content.ContentValues;
import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by cookab1 on 12/5/2017.
 */

public class JobManager {
    private static JobManager sJobManager;
    private List<Job> jobs;

    public static JobManager get(Context context) {
        if (sJobManager == null) {
            sJobManager = new JobManager();
        }
        return sJobManager;
    }
    private JobManager () {
        jobs = new ArrayList<Job>();
    }

    public void addJob(Job c) {
        jobs.add(c);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    //returns the job that is being looked for by the name
    public Job getJob(String name) {
        Job job = null;
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getName().equals(name)) {
                job = jobs.get(i);
            }
        }
        if(job == null) {
            System.out.println("Job not found in the jobs ArrayList.\nPlease ender a valid job name.");
        }
        return job;
    }

    //This function updates the specified job (name) in the jobs ArrayList.
    public void updateJob(String name, Job newJob) {
        int index = getIndex(name);

        jobs.set(index, newJob);
    }

    private int getIndex(String name) {
        int index = -1;
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getName().equals(name)) {
                index = i;
            }
        }
        if(index < 0) {
            System.out.println("Job not found in the jobs ArrayList.\nPlease ender a valid job name.");
        }
        return index;
    }
}
