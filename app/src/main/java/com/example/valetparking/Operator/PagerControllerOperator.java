package com.example.valetparking.Operator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControllerOperator extends FragmentPagerAdapter {
    int numTabs;

    public PagerControllerOperator(FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numTabs = behavior;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new CheckIn();
            case 1: return new OpenTicket();
            case 2: return new CheckOut();
            default: return null;
        }
    }

    @Override
    public int getCount() { return numTabs; }
}
