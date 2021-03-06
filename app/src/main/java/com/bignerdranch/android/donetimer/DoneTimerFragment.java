package com.bignerdranch.android.donetimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;


/**
 * Created by cookab1 on 11/21/2017.
 */

public class DoneTimerFragment extends Fragment {

    private static final String ARG_JOB_ID = "job_id";
    private static final long MIN_25 = 1500000; // 25 minutes in milliseconds
    private static final long MIN_10 = 600000; // 10 minutes in milliseconds
    private static final long MIN_5 = 300000; // 5 minutes in milliseconds

    private Job mJob;
    private EditText mTitleField;
    private CheckBox mFinishedCheckBox;
    private Button mShortBreak;
    private Button mLongBreak;
    private Button mWorkSession;
    private TextView mTimerText;
    private Button mStartButton;
    private Button mStopButton;
    private Button mResetButton;

    public CountDownTimer timer;
    public long time;
    public long timeWorked; //amount of time worked


    public static DoneTimerFragment newInstance(UUID jobID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_JOB_ID, jobID);

        DoneTimerFragment fragment = new DoneTimerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID jobId = (UUID) getArguments().getSerializable(ARG_JOB_ID);
        mJob = JobManager.get(getActivity()).getJob(jobId);
    }

    @Override
    public void onPause() {
        super.onPause();

        JobManager.get(getActivity()).updateJob(mJob);
    }

    private CountDownTimer Timer(final long time, final boolean work){
        return new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerText.setText(formatTime(millisUntilFinished));
                if (work)
                    timeWorked += 1000;
            }

            @Override
            public void onFinish() {
                mTimerText.setText("done!");
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);

        time = MIN_25;



        mTitleField = v.findViewById(R.id.task_title);
        mTitleField.setText(mJob.getName());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mJob.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mTimerText = v.findViewById(R.id.timer_text);

        mFinishedCheckBox = v.findViewById(R.id.task_finished);
        mFinishedCheckBox.setChecked(mJob.isFinished());
        mFinishedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mJob.finished();
                else
                    mJob.notFinished();
            }
        });

        mShortBreak = v.findViewById(R.id.short_break_button);
        mShortBreak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTimerText.setText("05:00");
                time = MIN_5;
            }
        });

        mLongBreak = v.findViewById(R.id.long_break_button);
        mLongBreak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTimerText.setText("10:00");
                time = MIN_10;
            }
        });

        mWorkSession = v.findViewById(R.id.work_session_button);
        mWorkSession.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTimerText.setText("25:00");
                time = MIN_25;
            }
        });

        mStartButton = v.findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mJob.started();
                if(time == MIN_25)
                    timer = Timer(time, true);
                else
                    timer = Timer(time, false);
                timer.start();
            }
        });

        mStopButton = v.findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(timer != null)
                    timer.cancel();
                //if(time == MIN_25)
                    //ArrayList<String> log = mJob.getLog().add("Worked for " + formatTime(timeWorked));
                mJob.addTime(timeWorked);
            }
        });

        mResetButton = v.findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mTimerText.setText(formatTime(time));
            }
        });

        return v;
    }

    private String formatTime(long time) {
        String format = "";
        long min = (time/1000) / 60;
        long sec = (time/1000) % 60;
        if (min < 10 || sec < 10)
            if (min < 10 && sec < 10)
                format = "0" + min + ":0" + sec;
            else if(min < 10)
                format = "0" + min + ":" + sec;
            else
                format = "" + min + ":0" + sec;
        else
            format = "" + min + ":" + sec;
        return format;
    }

/*
        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        mSuspectButton = v.findViewById(R.id.crime_suspect);
        mSuspectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });

        if(mJob.getSuspect() != null) {
            mSuspectButton.setText("Primary Suspect: " + mJob.getSuspect());
        }

        PackageManager packageManager = getActivity().getPackageManager();
        if(packageManager.resolveActivity(pickContact, PackageManager.MATCH_DEFAULT_ONLY) == null) {
            mSuspectButton.setEnabled(false);
        }


    private String getJobReport() {
        String solvedString = null;
        if (mJob.isSolved()) {
            solvedString = getString(R.string.crime_report_solved);
        } else {
            solvedString = getString(R.string.crime_report_unsolved);
        }

        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat, mJob.getDate()).toString();

        String suspect = mJob.getSuspect();
        if (suspect == null) {
            suspect = getString(R.string.crime_report_no_suspect);
        } else {
            suspect = getString(R.string.crime_report_suspect, suspect);
        }

        String report = getString(R.string.crime_report, mJob.getTitle(), dateString, solvedString, suspect);

        return report;
    }
    */
}
