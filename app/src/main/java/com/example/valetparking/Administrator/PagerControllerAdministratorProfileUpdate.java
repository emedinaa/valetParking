package com.example.valetparking.Administrator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControllerAdministratorProfileUpdate extends FragmentPagerAdapter {
    int numTabs;

    public PagerControllerAdministratorProfileUpdate(FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numTabs = behavior;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ProfileUpdateAdmin();
            case 1: return new ProfileUpdatePlace();
            case 2: return new ProfileUpdateLocation();
            default: return null;
        }
    }

    @Override
    public int getCount() { return numTabs; }
}
