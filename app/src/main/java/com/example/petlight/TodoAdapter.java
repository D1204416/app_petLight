package com.example.petlight;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class TodoAdapter extends ArrayAdapter<Pet> {

    public TodoAdapter(@NonNull Context context, ArrayList<Pet> Pets) {
        super(context, 0, Pets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pet pet = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_view, parent, false);
        }

        TextView tv_petName = (TextView) convertView.findViewById(R.id.petName);
        TextView tv_petBreed = (TextView) convertView.findViewById(R.id.petBreed);
        TextView tv_location = (TextView) convertView.findViewById(R.id.locationText);
        TextView tv_phone = (TextView) convertView.findViewById(R.id.phoneText);


        tv_petName.setText(pet.getPetName());
        tv_petBreed.setText(pet.getPetBreed());
        tv_location.setText(pet.getLocation());
        tv_phone.setText(pet.getPhone());


        ImageView imageView = (ImageView) convertView.findViewById(R.id.petImage);
        String imgName = pet.getImgName();

        if (imgName != null && !imgName.isEmpty()) {
            int imgResId = getContext().getResources().getIdentifier(imgName, "drawable", getContext().getPackageName());
            if (imgResId != 0) {
                imageView.setImageResource(imgResId);
            } else {
                imageView.setImageResource(R.drawable.sample_pet_image); // 預設圖片
            }
        } else {
            imageView.setImageResource(R.drawable.sample_pet_image); // 預設圖片
        }

        return convertView;
    }
}



