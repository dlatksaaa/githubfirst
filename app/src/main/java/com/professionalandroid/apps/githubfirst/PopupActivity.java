package com.professionalandroid.apps.githubfirst;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import com.professionalandroid.apps.githubfirst.R;

import java.util.ArrayList;
import java.util.List;

public class PopupActivity extends Activity {


    ListView list;
    ArrayList<Music> viewList = new ArrayList<>();
    MusicAdepter musicAdepter;
    JCSharingPreferences preferences;
    MainActivity mainActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_activity);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        list = findViewById(R.id.music_list);

        preferences = new JCSharingPreferences(getApplication());

        viewList.addAll(MainActivity.instance.musicArrayList);
        musicAdepter = new MusicAdepter(this, viewList);

        list.setAdapter(musicAdepter);
        musicAdepter.notifyDataSetChanged();



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = ((Music) parent.getItemAtPosition(position)).getCategory();
                String name = ((Music) parent.getItemAtPosition(position)).getName();
                preferences.putKey("category",category);
                preferences.putKey("name",name);

                MainActivity.instance.mediaPlayer.stop();
                MainActivity.instance.mediaPlayer2.stop();
                MainActivity.instance.mediaPlayer3.stop();
                MainActivity.instance.createMusic();
                MainActivity.instance.musicPropile.setText(category+" "+name);
                MainActivity.instance.musicPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (name == "롤린"  ){
                            MainActivity.instance.mediaPlayer.start();
                            MainActivity.instance.musicPropile.setText(category+" "+name);

                        }
                        else  if (name == "노래1" ){
                            MainActivity.instance.mediaPlayer2.start();
                            MainActivity.instance.musicPropile.setText(category+" "+name);
                        }
                        else if (name == "노래2"){
                            MainActivity.instance.mediaPlayer3.start();
                            MainActivity.instance.musicPropile.setText(category+" "+name);
                        }else {
                            MainActivity.instance.mediaPlayer.start();
                            MainActivity.instance.musicPropile.setText(category+" "+name);
                        }
                    }
                });

                finish();
            }
        });


    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        finish();
    }

    //바깥레이어 클릭시 안닫히게 만들기
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        return;
    }
}


