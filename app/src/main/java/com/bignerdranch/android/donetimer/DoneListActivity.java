package com.bignerdranch.android.donetimer;

import android.support.v4.app.Fragment;

/**
 * Created by cookab1 on 12/5/2017.
 */

public class DoneListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DoneListFragment();
    }
}
