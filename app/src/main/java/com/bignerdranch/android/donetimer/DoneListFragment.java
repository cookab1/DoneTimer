package com.bignerdranch.android.donetimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by cookab1 on 12/5/2017.
 */

public class DoneListFragment extends Fragment {
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mJobRecyclerView;
    private JobAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private class JobHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mTimeTextView;
        private ImageView mSolvedImageView;
        private ImageView mSelectImage;
        private Job mJob;

        public JobHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.task_title);
            mTimeTextView = itemView.findViewById(R.id.task_time);
            mSolvedImageView = itemView.findViewById(R.id.task_progress);
            mSelectImage = itemView.findViewById(R.id.select_image);
        }

        public void bind(Job job) {
            mJob = job;
            mTitleTextView.setText(mJob.getName());
            mTimeTextView.setText("00:00");
            mSolvedImageView.setVisibility(job.isFinished() ? View.VISIBLE : View.GONE);
            mSelectImage.setVisibility(View.VISIBLE);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                    mJob.getName() + " clicked!", Toast.LENGTH_SHORT)
                    .show();
            //Intent intent = DoneActivity.newIntent(getActivity(), mJob.getId());
            Intent intent = DonePagerActivity.newIntent(getActivity(), mJob.getId());
            startActivity(intent);
        }
    }

    private class JobAdapter extends RecyclerView.Adapter<JobHolder> {
        private List<Job> mJobs;

        public JobAdapter(List<Job> jobs) {
            mJobs = jobs;
        }

        @Override
        public JobHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new JobHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(JobHolder holder, int position) {
            Job job = mJobs.get(position);
            holder.bind(job);
        }

        @Override
        public int getItemCount() {
            return mJobs.size();
        }

        public void setJobs(List<Job> jobs) {
            mJobs = jobs;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_list, container, false);

        mJobRecyclerView = view
                .findViewById(R.id.task_recycler_view);
        mJobRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null)
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.item_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible)
            subtitleItem.setTitle(R.string.hide_subtitle);
        else
            subtitleItem.setTitle(R.string.show_subtitle);
    }

    //@Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.new_job:
                Job job = new Job();
            JobManager.get(getActivity()).addJob(job);
            Intent intent = DonePagerActivity.newIntent(getActivity(), job.getId());
            startActivity(intent);
            return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        JobManager jobManager = JobManager.get(getActivity());
        int jobCount = jobManager.getJobs().size();
        String subtitle = getString(R.string.subtitle_format,  jobCount);

        if(!mSubtitleVisible)
            subtitle = null;

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        JobManager jobManager = JobManager.get(getActivity());
        List<Job> jobs = jobManager.getJobs();

        if (mAdapter == null) {
            mAdapter = new JobAdapter(jobs);
            mJobRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setJobs(jobs);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

}
