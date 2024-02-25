package br.com.edsonvieira.salaodeunhas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.principal_opcoes, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuItemClicado = item.getItemId();

        if(menuItemClicado == R.id.menuItemCliente){
                //abre activity lista de clientes
        }
        if(menuItemClicado == R.id.menuItemServico){
                //abre activity lista de servicos
        }
        if (menuItemClicado == R.id.menuItemSobre){

            SobreActivity.nova(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}