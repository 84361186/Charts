package com.xq.mycharts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

/**
 * Created by SumT on 2016/12/20.
 */

public class LineGraph extends AppCompatActivity {
    GraphView graph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linechart);

        initChart();
    }

    private void initChart() {
        graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 5),
                new DataPoint(1, 3),
                new DataPoint(2, 4),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        PointsGraphSeries<DataPoint> pointseries = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(4, 6)
        });
        pointseries.setShape(PointsGraphSeries.Shape.POINT);
        pointseries.setColor(Color.RED);

        graph.addSeries(series);
        graph.addSeries(pointseries);

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setVerticalLabels(new String[] {"0", "10", "20"});
        staticLabelsFormatter.setHorizontalLabels(new String[] {"10:00", "12:00", "14:00","16:00","18:00"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(Color.WHITE);
        graph.getGridLabelRenderer().setVerticalAxisTitle("dB");

    }

}
