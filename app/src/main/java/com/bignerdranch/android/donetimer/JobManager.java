package com.bignerdranch.android.donetimer;

import android.content.Context;

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
        /*
        for (int i = 0; i < 100; i++) {
            Job job = new Job();
            job.setName("Job #" + i);
            if(i % 2 == 0)
                job.finished();
            jobs.add(job);
        }
        */
    }

    public void addJob(Job job) {
        jobs.add(job);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    //returns the job that is being looked for by the name
    public Job getJob(UUID id) {
        Job job = null;
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getId() == id) {
                job = jobs.get(i);
            }
        }
        if(job == null) {
            System.out.println("Job not found in the jobs ArrayList.\nPlease enter a valid job name.");
        }
        return job;
    }

    //This function updates the specified job (name) in the jobs ArrayList.
    public void updateJob(Job newJob) {
        int index = getIndex(newJob.getId());

        jobs.set(index, newJob);
    }

    private int getIndex(UUID id) {
        int index = -1;
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).getId() == id) {
                index = i;
            }
        }
        if(index < 0) {
            System.out.println("Job not found in the jobs ArrayList.\nPlease ender a valid job name.");
        }
        return index;
    }
}
