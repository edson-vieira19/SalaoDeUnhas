package br.com.edsonvieira.salaodeunhas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class PrincipalActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        ViewPageAdapter viewPageAdapter =
                new ViewPageAdapter(getSupportFragmentManager(),
                        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPageAdapter.addFragment(new DiaFragment(),"Dia");
        viewPageAdapter.addFragment(new SemanaFragment(),"Semana");
        viewPageAdapter.addFragment(new MesFragment(),"Mes");

        viewPager.setAdapter(viewPageAdapter);

    }
}