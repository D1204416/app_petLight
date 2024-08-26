package com.example.petlight;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        FrameLayout frameLayout = findViewById(R.id.upload_frame);


        GradientDrawable shape3 = new GradientDrawable();
        shape3.setShape(GradientDrawable.RECTANGLE);
        shape3.setColor(getResources().getColor(android.R.color.white)); // 背景颜色，可以根據需要進行修改
        float[] radii3 = {50f, 50f, 50f, 50f, 0f, 0f, 0f, 0f}; // 上左，上右，下右，下左圆角半徑
        shape3.setCornerRadii(radii3); // 設置各個角的圆角半徑

        frameLayout.setBackground(shape3);



        ListView listView = findViewById(R.id.listView);
        Button buttonBack = findViewById(R.id.button_back);
        Button buttonBackMain = findViewById(R.id.button_back_search);

        // 直接使用 AddActivity 中的靜態列表
        PetInfoAdapter adapter = new PetInfoAdapter(this, AddActivity.petInfoList);
        listView.setAdapter(adapter);

        // 設置反回按鈕的點擊事件，返回 AddActivity
        buttonBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(DisplayActivity.this, AddActivity.class);
            startActivity(backIntent);
            finish(); // 结束當前活動

        });

        // 設置反回按鈕的點擊事件，返回 MainActivity
        buttonBackMain.setOnClickListener(v -> {
            Intent backIntent = new Intent(DisplayActivity.this, MainActivity.class);
            startActivity(backIntent);
            finish(); // 结束當前活動

        });
    }
}
