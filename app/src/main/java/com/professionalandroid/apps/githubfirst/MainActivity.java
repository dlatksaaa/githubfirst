package com.professionalandroid.apps.githubfirst;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;
    Button musicPlay, musicStop, musicChoise;
    MediaPlayer mediaPlayer, mediaPlayer2, mediaPlayer3;
    ArrayList<Music> musicArrayList = new ArrayList<>();
    static public MainActivity  instance;
    JCSharingPreferences preferences;
    TextView musicPropile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

       // ActionBar actionBar = getSupportActionBar();
      //  actionBar.hide();

        lineChart = (LineChart)findViewById(R.id.chart);
        musicPlay = (Button) findViewById(R.id.music_play);
        musicStop = (Button) findViewById(R.id.music_stop);
        musicChoise = (Button) findViewById(R.id.music_choise);
        musicPropile = (TextView) findViewById(R.id.musicPropile);

        setMusic();
        preferences = new JCSharingPreferences(getApplication());


        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music1);
        mediaPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.music2);
        mediaPlayer3 = MediaPlayer.create(MainActivity.this, R.raw.music3);

        mediaPlayer.setLooping(false);
        mediaPlayer2.setLooping(false);
        mediaPlayer3.setLooping(false);

        musicChoise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                startActivity(intent);
            }
        });


        musicPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category= preferences.getValue("category","");
                String name= preferences.getValue("name","");

                if (name == "롤린"  ){
                mediaPlayer.start();
                musicPropile.setText(category+" "+name);

                }
               else  if (name == "노래1" ){
                    mediaPlayer2.start();
                    musicPropile.setText(category+" "+name);
                }
               else if (name == "노래2"){
                    mediaPlayer3.start();
                    musicPropile.setText(category+" "+name);
                }else {
                    mediaPlayer.start();
                    musicPropile.setText(category+" "+name);
                }
            }

            //  mediaPlayer.reset();
            //    mediaPlayer.release();
        });
        musicStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String category= preferences.getValue("category","");
                String name= preferences.getValue("name","");

                if (name== "롤린"){
                    mediaPlayer.pause();
                }
                if (name == "노래1"){
                    mediaPlayer2.pause();
                }
                if (name== "노래2"){
                    mediaPlayer3.pause();
                } else {
                    mediaPlayer.pause();

                }

            }
        });
        musicStop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mediaPlayer.stop();
                mediaPlayer2.stop();
                mediaPlayer3.stop();

                createMusic();

                Toast.makeText(getApplicationContext(),"노래가 정지되었습니다.", Toast.LENGTH_SHORT).show();


                return true;
            }
        });


        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1f, 2));
        entries.add(new Entry(2f, 1));
        entries.add(new Entry(3f, 3));
        entries.add(new Entry(4f, 5));
        entries.add(new Entry(5f, 3));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(7f, 7));
        entries.add(new Entry(8f, 6));


        LineDataSet lineDataSet = new LineDataSet(entries,"값");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(5); //각 x값 꼭지점 원 크기
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC")); //각 x값 꼭지점 원 색상
        lineDataSet.setCircleColorHole(Color.BLUE); // 꼭지점 원 가운데 색상
        lineDataSet.setColor(Color.parseColor("#ffffff")); //그래프 색상값
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
        lineDataSet.setDrawFilled(false); //선아래로 색상 표시 기본값 false
        lineDataSet.setDrawValues(false);


        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setVisibleXRangeMaximum(150);

        XAxis xAxis;
        xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //x축 포지션
        xAxis.setTextColor(Color.WHITE); //x축 텍스트 색상.
        xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);

        YAxis yRAxis = lineChart.getAxisRight();
        yRAxis.setDrawLabels(false);
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);
        yRAxis.setTextColor(Color.WHITE); //y측 우측 텍스트 색상
        yLAxis.setTextColor(Color.WHITE); //y축 좌측 텍스트 색상
        Description description = new Description();
        description.setText("");

        //라벨 관련
        Legend legend = lineChart.getLegend();
        legend.setTextColor(Color.WHITE);  //라벨 텍스트 색상 변경
        legend.setTextSize(16f);


        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();

        MyMarkerView marker = new MyMarkerView(this,R.layout.markerviewtext);
        marker.setChartView(lineChart);
        lineChart.setMarker(marker);



    }
    public void setMusic(){
        musicArrayList.add(new Music("롤린","브레이브 걸스"));
        musicArrayList.add(new Music("노래1","아이유"));
        musicArrayList.add(new Music("노래2","아이유"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }
    public void createMusic(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music1);
        mediaPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.music2);
        mediaPlayer3 = MediaPlayer.create(MainActivity.this, R.raw.music3);

    }

}