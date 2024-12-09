package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import com.example.proyecto.Adapters.AdapterTab;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configuración del AdapterTab
        AdapterTab adapterTab = new AdapterTab(this);

        // Configuración del ViewPager2
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(adapterTab);

        // Configuración del TabLayout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Todos");
                        break;
                    case 1:
                        tab.setText("Europa");
                        break;
                    case 2:
                        tab.setText("Africa");
                        break;
                    case 3:
                        tab.setText("Asia");
                        break;
                    case 4:
                        tab.setText("Oceania");
                        break;
                    case 5:
                        tab.setText("America");
                        break;
                    case 6:
                        tab.setText("Antártida");
                        break;
                }
            }
        }).attach();

        // Configuración del Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuración del DrawerLayout y NavigationView
        DrawerLayout drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getTitle().equals("Cerrar sesion")) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showCountryDetails(PaisesModel country) {
        //Crea una instancia con los detalles del pais
        InformacionPaises fragment = InformacionPaises.newInstance(country);

        // Mostrar el contenedor de fragmentos
        findViewById(R.id.informacion_container).setVisibility(View.VISIBLE);

        // Ocultar el ViewPager2
        findViewById(R.id.viewPager2).setVisibility(View.GONE);

        // Realizar la transacción para reemplazar el fragmento
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.informacion_container, fragment);
        transaction.addToBackStack(null); // Permitir navegación hacia atrás
        transaction.commit();
    }

}
