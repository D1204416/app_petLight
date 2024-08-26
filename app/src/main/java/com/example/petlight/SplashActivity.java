package com.example.petlight;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000; // 延遲2秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 初始化 ImageView 進行亮燈效果
        ImageView lightImage = findViewById(R.id.light_image);

        // 創建 ObjectAnimator 以改變 Alpha 屬性來實現亮燈效果
        ObjectAnimator lightAnimator = ObjectAnimator.ofFloat(lightImage, "alpha", 0f, 1f);
        lightAnimator.setDuration(1500); // 動畫持續時間 2300 毫秒
        lightAnimator.setInterpolator(new LinearInterpolator()); // 過渡效果


        // 啟動動畫
        lightAnimator.start();

        // 延遲後跳轉到主界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止動畫
                lightAnimator.cancel();

                // 跳轉到主界面
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
