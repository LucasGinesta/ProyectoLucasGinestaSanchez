package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.proyecto.Adapters.AdapterTab;
import com.example.proyecto.Fragments.Africa;
import com.example.proyecto.Fragments.America;
import com.example.proyecto.Fragments.Antartida;
import com.example.proyecto.Fragments.Asia;
import com.example.proyecto.Fragments.Europa;
import com.example.proyecto.Fragments.Oceania;
import com.example.proyecto.Fragments.Total;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * La clase `MainActivity` es la actividad principal de la aplicación,
 * que gestiona la visualización de los países en diferentes regiones
 * a través de un `ViewPager2` con pestañas, y proporciona la funcionalidad
 * de búsqueda y navegación entre los países.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Método llamado al crear la actividad. Inicializa el entorno de la
     * actividad, incluidos los fragmentos y componentes de la interfaz.
     *
     * @param savedInstanceState Estado guardado de la actividad, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Habilita el modo de pantalla completa.
        setContentView(R.layout.activity_main);

        // Obtención del ViewModel que gestiona los países
        PaisesViewModel paisesViewModel = new ViewModelProvider(this).get(PaisesViewModel.class);
        paisesViewModel.obtenerPaisesDesdeAPI();  // Carga los datos de países desde la API

        // Configuración del adaptador del ViewPager
        AdapterTab adapterTab = new AdapterTab(this);

        // Configuración del ViewPager2
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(adapterTab);

        // Configuración del TabLayout para las pestañas
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                // Configura el texto de las pestañas según la posición
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
        }).attach();  // Adjunta el TabLayout al ViewPager2

        // Configuración del Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuración del SearchView para la búsqueda de países
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;  // No realiza acción al enviar el texto
            }

            /**
             * Filtra la lista de países en el fragmento actual según el texto ingresado.
             *
             * @param newText Texto ingresado en el SearchView para realizar el filtro.
             * @return `true` si se procesa correctamente el texto ingresado.
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                // Obtiene el fragmento actual visible en el ViewPager2
                Fragment currentFragment = adapterTab.getFragment(viewPager2.getCurrentItem());

                // Filtra la lista según el fragmento visible
                switch (currentFragment.getClass().getSimpleName()) {
                    case "Total":
                        ((Total) currentFragment).findLista(newText);
                        break;
                    case "Europa":
                        ((Europa) currentFragment).findLista(newText);
                        break;
                    case "Africa":
                        ((Africa) currentFragment).findLista(newText);
                        break;
                    case "Asia":
                        ((Asia) currentFragment).findLista(newText);
                        break;
                    case "America":
                        ((America) currentFragment).findLista(newText);
                        break;
                    case "Oceania":
                        ((Oceania) currentFragment).findLista(newText);
                        break;
                    case "Antartida":
                        ((Antartida) currentFragment).findLista(newText);
                        break;
                    default:
                        break;
                }

                return true;
            }
        });

        // Configuración del DrawerLayout y NavigationView
        DrawerLayout drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.navView);

        // Establece "Inicio" como seleccionado por defecto en el menú de navegación
        navigationView.setCheckedItem(R.id.inicio);

        // Establece el listener para los elementos del menú de navegación
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.salir) {
                // Lógica para salir de la aplicación
                startActivity(new Intent(MainActivity.this, Salir.class));
            } else if (item.getItemId() == R.id.inicio) {
                // No se realiza acción, ya que "Inicio" está seleccionado por defecto
            }
            drawerLayout.closeDrawer(GravityCompat.START);  // Cierra el menú lateral
            return true;
        });

        // Establece los márgenes del sistema en el DrawerLayout para la pantalla completa
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Muestra los detalles de un país en un fragmento específico.
     *
     * @param pais El modelo de país que contiene los detalles a mostrar.
     */
    public void showCountryDetails(PaisesModel pais) {
        // Crea una nueva instancia del fragmento con los detalles del país
        InformacionPaises fragment = InformacionPaises.newInstance(pais);

        // Muestra el contenedor de fragmentos
        findViewById(R.id.informacion_container).setVisibility(View.VISIBLE);

        // Oculta el ViewPager2
        findViewById(R.id.viewPager2).setVisibility(View.GONE);

        // Realiza la transacción para reemplazar el fragmento actual con los detalles del país
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.informacion_container, fragment);
        transaction.addToBackStack(null);  // Permite volver al fragmento anterior
        transaction.commit();
    }
}
