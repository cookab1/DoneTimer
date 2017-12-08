package com.bignerdranch.android.donetimer;

import android.os.Bundle;
import android.support.v4.app.Fragment;


/**
 * Created by cookab1 on 11/21/2017.
 */

public class DoneTimerFragment extends Fragment {

    private static final String ARG_JOB_ID = "job_id";


    public static DoneTimerFragment newInstance(String jobName) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_JOB_ID, jobName);

        DoneTimerFragment fragment = new DoneTimerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
