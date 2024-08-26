package com.example.petlight;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PetInfoAdapter extends ArrayAdapter<PetInfo> {

  private static final int REQUEST_CALL_PERMISSION = 1;

  public PetInfoAdapter(Context context, List<PetInfo> petInfoList) {
    super(context, 0, petInfoList);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_pet_info, parent, false);
    }

    PetInfo petInfo = getItem(position);

    ImageView imageView = convertView.findViewById(R.id.imageView);
    TextView textViewName = convertView.findViewById(R.id.textView_name);
    TextView textViewBreed = convertView.findViewById(R.id.textView_breed);
    TextView textViewLocation = convertView.findViewById(R.id.textView_location);
    TextView textViewPhone = convertView.findViewById(R.id.textView_phone);
    ImageView iconCall = convertView.findViewById(R.id.icon_call);
    ImageView iconMap = convertView.findViewById(R.id.icon_map);

    if (petInfo != null) {
      imageView.setImageBitmap(petInfo.getImage());
      textViewName.setText(petInfo.getName());
      textViewBreed.setText(petInfo.getBreed());
      textViewLocation.setText(petInfo.getLocation());
      textViewPhone.setText(petInfo.getPhone());

      // 撥打電話click事件
      iconCall.setOnClickListener(v -> {
        Log.d("PetInfoAdapter", "Call icon clicked");
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + petInfo.getPhone()));
//        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
          getContext().startActivity(intent);
//        }  else {
//          Log.e("PetInfoAdapter", "No application found to handle dial intent");
//        }
      });


      // 點擊地圖icon事件
      iconMap.setOnClickListener(v -> {
        Log.d("PetInfoAdapter", "Map icon clicked");
        String location = petInfo.getLocation();
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoUri);
//        if (mapIntent.resolveActivity(getContext().getPackageManager()) != null) {
          getContext().startActivity(mapIntent);
//        } else {
//          Log.e("PetInfoAdapter", "No application found to handle map intent");
//        }
      });
    }

    return convertView;
  }
}


