package com.example.valetparking.Administrator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControllerAdministrator extends FragmentPagerAdapter {
    int numTabs;
    String Id;

    public PagerControllerAdministrator(FragmentManager fm, int behavior, String id) {
        super(fm, behavior);
        this.numTabs = behavior;
        Id = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Tickets(Id);
            case 1: return new CancelVehicle();
            case 2: return new CreateAccountOperator();
            default: return null;
        }
    }

    @Override
    public int getCount() { return numTabs; }
}
