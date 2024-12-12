package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Activity que maneja la pantalla de salida de la aplicación.
 * Permite al usuario salir de la aplicación o volver a la pantalla principal.
 */
public class Salir extends AppCompatActivity {

    /**
     * Método que se llama cuando la actividad se crea.
     * Se configura la interfaz de usuario, incluidos los botones de salida e inicio de sesión.
     *
     * @param savedInstanceState El estado guardado de la actividad anterior, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Habilita el modo de borde a borde para la actividad.
        setContentView(R.layout.activity_login);  // Establece el diseño de la actividad.

        // Configuración del botón de iniciar sesión.
        Button buttonLogin = findViewById(R.id.buttonIniciar);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad principal y finaliza la actividad actual.
                Intent intent = new Intent(Salir.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Configuración del botón de salir.
        Button buttonSalir = findViewById(R.id.buttonSalir);
        buttonSalir.setOnClickListener(view -> salirAplicacion());  // Llama al método para salir de la aplicación.

        // Configuración de los márgenes y padding de la interfaz teniendo en cuenta los elementos del sistema.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Método para salir de la aplicación.
     * Inicia la actividad principal del sistema para "ocultar" la aplicación y finalizar la actividad actual.
     */
    private void salirAplicacion() {
        // Crea una intención para ir a la pantalla de inicio del dispositivo.
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  // Inicia la actividad en una nueva tarea.
        startActivity(intent);
        finish();  // Finaliza la actividad actual.
    }
}
