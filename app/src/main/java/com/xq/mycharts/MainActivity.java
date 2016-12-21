package com.xq.mycharts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private PieChart mChart;
    protected String[] mParties = new String[]{"Deep","REM", "Light"};
    private Button mp;
    private Button graph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initChart();
        mp = (Button) findViewById(R.id.mp);
        graph = (Button) findViewById(R.id.graph);

        mp.setOnClickListener(this);
        graph.setOnClickListener(this);
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their
        // position around the center of
        // the chart.
        /*for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), mParties[i %
            mParties.length]));
        }*/
        entries.add(new PieEntry(5f, mParties[0]));
        entries.add(new PieEntry(25f, mParties[1]));
        entries.add(new PieEntry(70f, mParties[2]));


        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

       /* for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());*/
        colors.add(0xFFEC3712);
        colors.add(0xFF5BC0C0);
        colors.add(0xFFF8AC39);
        dataSet.setColors(colors);
        dataSet.setValueLineColor(0x00ffffff);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.1f);
        //dataSet.setSelectionShift(0f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        dataSet.setValueTextColors(colors);


        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(14f);
        data.setValueTextColors(colors);
        //data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private void initChart() {
        mChart = (PieChart) findViewById(R.id.piechart);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);

        //mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.TRANSPARENT);


        mChart.setTransparentCircleColor(Color.TRANSPARENT);
        //mChart.setTransparentCircleAlpha(50);

        mChart.setHoleRadius(75f);//半径
        //mChart.setTransparentCircleRadius(61f);//半透明圆

        mChart.setDrawCenterText(true);
        mChart.setNoDataText("暂无数据");

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        //mChart.setOnChartValueSelectedListener(this);

        setData(3, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        /*l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);*/
        l.setEnabled(false);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        //mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("87%\n13 hrs");
        s.setSpan(new RelativeSizeSpan(3.2f), 0, 3, 0);
        //s.setSpan(new StyleSpan(Typeface.NORMAL), 3, s.length() - 5, 0);
        //s.setSpan(new ForegroundColorSpan(Color.WHITE), 3, s.length() - 5, 0);
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), 0);
        s.setSpan(new RelativeSizeSpan(2.2f), 3, s.length() - 6, 0);
        //s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 4, s.length(), 0);
        return s;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mp:
                startActivity(new Intent(this,BarChartActivity.class));
                break;
            case R.id.graph:
                startActivity(new Intent(this,GraphBarChart.class));
                break;
        }
    }
}
