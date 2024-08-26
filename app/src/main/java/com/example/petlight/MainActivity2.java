package com.example.petlight;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private EditText editTextName, editTextBreed, editTextLocation, editTextPhone;
    private Bitmap selectedImageBitmap;

    public static List<PetInfo> petInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.imageView);
        Button buttonUpload = findViewById(R.id.button_upload);
        Button buttonConfirm = findViewById(R.id.button_confirm);
        Button buttonCancel = findViewById(R.id.button_cancel);
        editTextName = findViewById(R.id.editText_name);
        editTextBreed = findViewById(R.id.editText_breed);
        editTextLocation = findViewById(R.id.editText_location);
        editTextPhone = findViewById(R.id.editText_phone);

        buttonUpload.setOnClickListener(v -> openImageChooser());

        buttonConfirm.setOnClickListener(v -> showConfirmationDialog());

        buttonCancel.setOnClickListener(v -> finish());
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "选择图片"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                // 使用 BitmapFactory.Options 进行图片压缩
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;

                // 获取原始图片尺寸
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                BitmapFactory.decodeStream(inputStream, null, options);
                inputStream.close();

                // 计算压缩比例
                options.inSampleSize = calculateInSampleSize(options, 300, 300); // 目标宽高
                options.inJustDecodeBounds = false;

                // 再次打开图片并进行压缩
                inputStream = getContentResolver().openInputStream(imageUri);
                selectedImageBitmap = BitmapFactory.decodeStream(inputStream, null, options);
                inputStream.close();

                // 设置压缩后的图片到 ImageView
                imageView.setImageBitmap(selectedImageBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 计算 inSampleSize 方法，用于确定缩放比例
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
            .setTitle("確定新增?")
            .setMessage("確定內容並儲存")
            .setPositiveButton("確認", (dialog, which) -> savePetInfo())
            .setNegativeButton("取消", null)
            .show();
    }

    private void savePetInfo() {
        String name = editTextName.getText().toString();
        String breed = editTextBreed.getText().toString();
        String location = editTextLocation.getText().toString();
        String phone = editTextPhone.getText().toString();

        Bitmap image = null;
        if (selectedImageBitmap != null) {
            image = selectedImageBitmap;
        }

        PetInfo petInfo = new PetInfo(name, breed, location, phone, image);
        petInfoList.add(petInfo);

        Intent intent = new Intent(MainActivity2.this, DisplayActivity.class);
        startActivity(intent);
    }
}



