package com.professionalandroid.apps.githubfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView face, face2, face3;
        lineChart = (LineChart)findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1f, 1));
        entries.add(new Entry(2f, 2));
        entries.add(new Entry(3f, 0));
        entries.add(new Entry(4f, 5));
        entries.add(new Entry(5f, 3));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(7f, 4));
        entries.add(new Entry(8f, 5));


        LineDataSet lineDataSet = new LineDataSet(entries,"집중선");
        lineDataSet.setLabel("#FFFFFF");
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleRadius(5); //각 x값 꼭지점 원 크기
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC")); //각 x값 꼭지점 원 색상
        lineDataSet.setCircleColorHole(Color.BLUE); // 꼭지점 원 가운데 색상
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC")); //그래프 색상값
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(false);
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

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);
        lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart.invalidate();
        MyMarkerView marker = new MyMarkerView(this,R.layout.markerviewtext);
        marker.setChartView(lineChart);
        lineChart.setMarker(marker);
    }

}