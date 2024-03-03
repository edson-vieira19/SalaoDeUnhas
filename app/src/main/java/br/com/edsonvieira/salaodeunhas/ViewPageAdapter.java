package br.com.edsonvieira.salaodeunhas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragmentsList = new ArrayList<>();

    private final ArrayList<String> fragmentTitlesList = new ArrayList<>();




    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    public void addFragment(Fragment fragment, String title){

        fragmentsList.add(fragment);
        fragmentTitlesList.add(title);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return fragmentTitlesList.get(position);
    }
}
