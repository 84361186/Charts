package com.xq.mycharts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

/**
 * Created by SumT on 2016/12/20.
 */

public class GraphBarChart extends AppCompatActivity {
    private GraphView graph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphbarchart);

        initChart();
    }

    private void initChart() {
        graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0,3),
                new DataPoint(1,2),
                new DataPoint(2,2),
                new DataPoint(3,2),
                new DataPoint(4,-1),
                new DataPoint(5,1),
                new DataPoint(6,3),
                new DataPoint(7,3),
                new DataPoint(8,-1),
                new DataPoint(9,1),
                new DataPoint(10,1),
                new DataPoint(11,3),
                new DataPoint(12,3),
                new DataPoint(13,3)
        });
        graph.addSeries(series);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint dataPoint) {
                double yValue = dataPoint.getY();
                if(yValue < 0){
                    return 0xFFFFFFFF;
                }else if(yValue <= 1){
                      return  0xFF5AC1BF;
                    }else if(yValue <= 2){
                       return  0xFFF6AD3B;
                    }else if(yValue <= 3){
                       return 0xFFED3613;
                    }
                return Color.WHITE;
            }
        });
        series.setSpacing(0);
        /*graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    if(value == 0){
                        return "REM";
                    }else if(value == 1){
                        return "Deep";
                    }else if(value == 2){
                        return "Light";
                    }else if(value == 3){
                        return "Wake";
                    }
                    return " ";
                }
            }
        });*/
        StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph);
        staticLabelsFormatter1.setHorizontalLabels(new String[] {" ", "6am", " "," "," "," "," "," "," "," "," "," "," "," "," ","8pm"});
        staticLabelsFormatter1.setVerticalLabels(new String[] {" ", "Deep", "Light","Wake","REM"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL);
        graph.getGridLabelRenderer().setGridColor(Color.WHITE);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(24);

        //set second scale
        graph.getSecondScale().addSeries(series);
        StaticLabelsFormatter staticLabelsFormatter2 = new StaticLabelsFormatter(graph);
        staticLabelsFormatter2.setVerticalLabels(new String[] {" ", " ", " "," "," "});
        graph.getSecondScale().setLabelFormatter(staticLabelsFormatter2);

        graph.getGridLabelRenderer().setHorizontalLabelsVisible(true);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graph.getViewport().setScalable(true);

        graph.getViewport().setBorderColor(Color.WHITE);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-2);
        graph.getViewport().setMaxX(14);

    }
}
