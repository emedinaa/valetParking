package com.example.valetparking.Administrator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControllerAdministrator extends FragmentPagerAdapter {
    int numTabs;

    public PagerControllerAdministrator(FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numTabs = behavior;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new TicketsFragment();
            case 1: return new CancelVehicleFragment();
            case 2: return new CreateAccountOperatorFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() { return numTabs; }
}
