package com.example.petlight;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = findViewById(R.id.rounded_frame);


        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(getResources().getColor(android.R.color.white)); // 背景颜色，可以根據需要進行修改
        float[] radii = {50f, 50f, 50f, 50f, 0f, 0f, 0f, 0f}; // 上左，上右，下右，下左圆角半徑
        shape.setCornerRadii(radii); // 設置各個角的圆角半徑

        frameLayout.setBackground(shape);



        FrameLayout navLayout = findViewById(R.id.LoginRegister);

        GradientDrawable shape2 = new GradientDrawable();
        shape2.setShape(GradientDrawable.RECTANGLE);
        shape2.setColor(getResources().getColor(R.color.nav_color)); // 背景颜色，可以根據需要進行修改
        float[] radii2 = {50f, 50f, 50f, 50f, 0f, 0f, 0f, 0f}; // 上左，上右，下右，下左圆角半徑
        shape2.setCornerRadii(radii2); // 设置各个角的圆角半径

        // 設置邊框的颜色和寬度（例如：黄色和5像素寬度）
        shape2.setStroke(5, getResources().getColor(R.color.box_color));

        navLayout.setBackground(shape2);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // 寫另一個Activity回傳後, 得到回傳的資料之後的方法
                        if (result.getData() != null && result.getResultCode() == Activity.RESULT_OK) {

                        }
                    }
                }
        );
    }


    public void Goto(View view) {
        Intent intent = new Intent(this, DisplayActivity.class);

        intentActivityResultLauncher.launch(intent);

    }

}

