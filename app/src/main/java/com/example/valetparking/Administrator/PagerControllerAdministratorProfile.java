package com.example.valetparking.Administrator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControllerAdministratorProfile extends FragmentPagerAdapter {
    int numTabs;
    String ID;

    public PagerControllerAdministratorProfile(FragmentManager fm, int behavior, String id) {
        super(fm, behavior);
        this.numTabs = behavior;
        ID = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ProfileAdmin(ID);
            case 1: return new ProfilePlace(ID);
            default: return null;
        }
    }


    @Override
    public int getCount() { return numTabs; }
}
