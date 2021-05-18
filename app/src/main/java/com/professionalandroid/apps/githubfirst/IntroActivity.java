package com.professionalandroid.apps.githubfirst;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);

     //   ActionBar actionBar = getSupportActionBar();
      //  actionBar.hide();
      /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#ffffff"));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
*/

        ImageView gifImage = (ImageView) findViewById(R.id.introImage);
        Glide.with(getApplicationContext()).load(R.drawable.intro1).into(gifImage);
        startIntro();
    }
    private void startIntro() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);  //Loagin화면을 띄운다.
                finish();   //현재 액티비티 종료
            }
        }, 3000); // 화면에 Logo 3초간 보이기
    }
}
