package com.xq.mycharts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by SumT on 2016/12/19.
 */
public class MyYAxisValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if(value < 1){
            return "Deep";
        }else if(value < 2){
            return "Light";
        }else if(value < 3){
            return "Wake";
        }
        return " ";
    }
}
