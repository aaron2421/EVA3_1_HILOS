package com.example.eva3_1_hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button boton;
    TextView texto;
    Thread hilo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        texto = findViewById(R.id.textView);
        //Threads con hilos
         hilo = new Thread(){
            @Override
            public void run() {
                super.run();
                for (int a=0; a<10;a++){
                    try {
                        //texto.setText(""+a);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        /*Start: usa el metodo run que acabamos de sobreescribir en otro hilo aparte
        *Run:Ejecuta el metodo run en el mismo hilo de ejecucion actual
        */
        hilo.start();

        //Threads con Runnable(interfaces)
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int a=0; a<10;a++){
                    try {
                        //texto.setText(""+a);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread hiloRunnable = new Thread(runnable);
        hiloRunnable.start();


        boton = findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Working",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        hilo.interrupt(); //INTERRUMPIR EL HILO EN EL ESTADO ONSTOP
    }
}

