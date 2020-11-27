package br.com.technologies.venom.sensoracelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Desenvolvedor: Edgard Oliveira
 * Data de criação: 26/11/2020
 * Última Revisão: 26/11/2020
 * Objetivo: mostrar o monitoramento do sensor de acelerômetro
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Acelerometro>>>";
    private SensorManager sensorManager = null;
    private Sensor sensor;
    private TextView tvX, tvY, tvZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvX = findViewById(R.id.tvX);
        tvY = findViewById(R.id.tvY);
        tvZ = findViewById(R.id.tvZ);

    }

    @Override
    protected void onStart() {
        super.onStart();
        registrarOuvinte();
    }

    @Override
    protected void onDestroy() {
        desregistrarOuvinte();
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        tvX.setText("X: " + String.valueOf(x));
        tvY.setText("Y: " + String.valueOf(y));
        tvZ.setText("Z: " + String.valueOf(z));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void registrarOuvinte() {
        Log.d(TAG, "registrarOuvinte: Registrando ouvinte do sensor...");
        // Configura o gerenciador de sensores ao iniciar o serviço
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Configurando o tipo para sensor de acelerômetro
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Registrando um ouvinte do sensor
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void desregistrarOuvinte() {
        Log.d(TAG, "desregistrarOuvinte: Desregistrando ouvinte do sensor...");
        // Des-registra o ouvinte do sensor
        sensorManager.unregisterListener(this);
    }
}