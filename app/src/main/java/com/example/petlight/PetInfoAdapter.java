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
    ImageView iconshare = convertView.findViewById(R.id.icon_share);
    ImageView iconLightbulb = convertView.findViewById(R.id.icon_light_bulb);

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

    // Share icon click event
    iconshare.setOnClickListener(v -> {
      Log.d("PetInfoAdapter", "Share button clicked");
      String shareText = "寵物姓名: " + petInfo.getName() + "\n" +
              "寵物品種: " + petInfo.getBreed() + "\n" +
              "走失地點: " + petInfo.getLocation() + "\n" +
              "聯絡電話: " + petInfo.getPhone();

      Intent shareIntent = new Intent(Intent.ACTION_SEND);
      shareIntent.setType("text/plain");
      shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

      if (shareIntent.resolveActivity(getContext().getPackageManager()) != null) {
        getContext().startActivity(Intent.createChooser(shareIntent, "分享寵物信息"));
      } else {
        Log.e("PetInfoAdapter", "No application found to handle share intent");
      }
    });

    // Lightbulb icon click event
    iconLightbulb.setOnClickListener(this::startIconAnimation);
  }

        return convertView;
}

private void startIconAnimation(View view) {
  // Save the original properties
  float originalScaleX = view.getScaleX();
  float originalScaleY = view.getScaleY();
  float originalTranslationY = view.getTranslationY();
  float originalX = view.getX();
  float originalY = view.getY();

  // Get screen height and width
  int screenHeight = view.getRootView().getHeight();
  int screenWidth = view.getRootView().getWidth();

  // Calculate the bottom center position
  float targetX = screenWidth / 2f - view.getWidth() / 2f;
  float targetY = screenHeight - view.getHeight();

  // Phase 1: Move the icon to the bottom center without scaling
  view.animate()
          .x(targetX)
          .y(targetY)
          .setDuration(2000)  // Duration for moving to bottom center
          .withEndAction(() -> {
            // Phase 2: Move the icon upwards while gradually scaling it up
            view.animate()
                    .translationY(-screenHeight)  // Move up by the height of the screen
                    .scaleX(7f)  // Gradually scale up
                    .scaleY(7f)
                    .setDuration(9000)  // Duration for moving upwards and scaling
                    .withEndAction(() -> {
                      // Return to original state
                      view.animate()
                              .scaleX(originalScaleX)
                              .scaleY(originalScaleY)
                              .translationY(originalTranslationY)
                              .x(originalX)
                              .y(originalY)
                              .setDuration(1000)  // Duration for returning to original state
                              .start();
                    })
                    .start();
          })
          .start();
}
}