package com.utsas212102375.myapplication;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner _spinner1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _spinner1 = findViewById(R.id.spinner1);

        initSpinner1();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button infoButton;
        infoButton = findViewById(R.id.info_button);
        infoButton.setOnClickListener(v -> showNotification());
    }

    private void initSpinner1() {
        List<String> JenisKelaminList = new ArrayList<>();
        JenisKelaminList.add("Jenis Kelamin");
        JenisKelaminList.add("Laki-Laki");
        JenisKelaminList.add("Perempuan");
        JenisKelaminList.add("Indonesia");
        JenisKelaminList.add("Español");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, JenisKelaminList);
        _spinner1.setAdapter(arrayAdapter);
    }

    private void showNotification() {
        String selectedLanguage = _spinner1.getSelectedItem().toString();

        String notificationContent;
        switch (selectedLanguage) {
            case "Indonesia":
            case "Español":
            default:
                notificationContent = "212102375 - Septi Trie Wahyudhi";
                break;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Informasi")
                .setContentText(notificationContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        int notificationId = 123;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Consider requesting the permission here if it's not granted.
            return;
        }
        notificationManager.notify(notificationId, builder.build());
    }
}
