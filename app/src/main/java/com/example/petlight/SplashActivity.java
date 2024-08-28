package com.example.petlight;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2500; // 延遲2秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 更改狀態列的顏色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.nav_color)); // 替換為你的顏色資源
        }


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
