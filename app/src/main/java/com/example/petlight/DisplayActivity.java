package com.example.petlight;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

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
