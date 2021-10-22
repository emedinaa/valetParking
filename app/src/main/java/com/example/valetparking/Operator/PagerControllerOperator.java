package com.example.valetparking.Operator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControllerOperator extends FragmentPagerAdapter {
    int numTabs;
    String Id;

    public PagerControllerOperator(FragmentManager fm, int behavior, String id) {
        super(fm, behavior);
        this.numTabs = behavior;
        Id = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new CheckIn(Id);
            case 1: return new OpenTicket(Id);
            case 2: return new CheckOut(Id);
            default: return null;
        }
    }

    @Override
    public int getCount() { return numTabs; }
}
