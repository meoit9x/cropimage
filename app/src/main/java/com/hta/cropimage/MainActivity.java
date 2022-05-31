package com.hta.cropimage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> images = new ArrayList<>();
    private static final int REQUEST_PERMISSIONS = 100;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView gallery = findViewById(R.id.galleryGridView);
        gallery.setLayoutManager(new GridLayoutManager(this, 3));
        imageAdapter = new ImageAdapter(this);
        gallery.setAdapter(imageAdapter);

        if (Utils.isStoragePermissionGranted(this))
            initData();
    }

    private void initData() {
        images = Utils.getAllShownImagesPath(this);
        imageAdapter.setWithScreen(Utils.getWidthScreen(this));
        imageAdapter.setData(images);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initData();
        }
    }
}