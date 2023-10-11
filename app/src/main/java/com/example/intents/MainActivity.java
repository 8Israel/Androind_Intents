package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView contadorTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contadorTextView = findViewById(R.id.contadorTextView);
        Button btn1 = findViewById(R.id.btn1);

        long tiempoTotal = 5000; // 10 segundos en milisegundos
        long intervalo = 1000; // 1 segundo en milisegundos

        CountDownTimer countDownTimer = new CountDownTimer(tiempoTotal, intervalo) {
            @Override
            public void onTick(long millisUntilFinished) {
                String tiempoFormateado = formatTiempo(millisUntilFinished);
                contadorTextView.setText("Tiempo restante: " + tiempoFormateado);
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();
        btn1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                        startActivity(intent);
                                        finish();
                                        countDownTimer.cancel();

                                    }
                                }

        );
    }
    // Método para formatear el tiempo en HH:mm:ss
    private String formatTiempo(long millis) {
        int segundos = (int) (millis / 1000) % 60;
        int minutos = (int) ((millis / (1000 * 60)) % 60);
        int horas = (int) ((millis / (1000 * 60 * 60)) % 24);

        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }
}