package com.bignerdranch.android.donetimer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by cookab1 on 12/5/2017.
 */

public class DonePagerActivity extends AppCompatActivity {
    private static final String EXTRA_JOB_ID =
            "com.bignerdranch.android.donetimer.job_id";

    private ViewPager mViewPager;
    private List<Job> mJob;

    public static Intent newIntent(Context packageContext, UUID jobId) {
        Intent intent = new Intent(packageContext, DonePagerActivity.class);
        intent.putExtra(EXTRA_JOB_ID, jobId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_pager);

        UUID jobId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_JOB_ID);

        mViewPager = (ViewPager) findViewById(R.id.job_view_pager);

        mJob = JobManager.get(this).getJobs();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Job job = mJob.get(position);
                return DoneTimerFragment.newInstance(job.getId());
            }

            @Override
            public int getCount() {
                return mJob.size();
            }

        });

        for (int i = 0; i < mJob.size(); i++) {
            if (mJob.get(i).getName().equals(jobId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
