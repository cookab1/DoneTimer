package com.bignerdranch.android.donetimer;

import android.os.Bundle;
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

import java.util.UUID;


/**
 * Created by cookab1 on 11/21/2017.
 */

public class DoneTimerFragment extends Fragment {

    private static final String ARG_JOB_ID = "job_id";

    private Job mJob;
    private EditText mTitleField;
    private CheckBox mFinishedCheckBox;
    private Button mShortBreak;
    private Button mLongBreak;
    private Button mWorkSession;
    private Button mStartButton;
    private Button mStopButton;
    private Button mResetButton;


    public static DoneTimerFragment newInstance(String jobName) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_JOB_ID, jobName);

        DoneTimerFragment fragment = new DoneTimerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_JOB_ID);
        mJob = JobManager.get(getActivity()).getJob(crimeId);
    }

    @Override
    public void onPause() {
        super.onPause();

        //JobManager.get(getActivity()).updateJob(mJob);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);

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

            }
        });

        mLongBreak = v.findViewById(R.id.short_break_button);
        mLongBreak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


        mWorkSession = v.findViewById(R.id.short_break_button);
        mWorkSession.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


        mStartButton = v.findViewById(R.id.short_break_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


        mStopButton = v.findViewById(R.id.short_break_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


        mResetButton = v.findViewById(R.id.short_break_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        return v;
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
