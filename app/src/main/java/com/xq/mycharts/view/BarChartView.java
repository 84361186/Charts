package com.xq.mycharts.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.CustomLineData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BarChartView extends DemoView{

	private String TAG = "BarChartView";
	private BarChart chart = new BarChart();
	private BarChart secondChart = new BarChart();
	//轴数据源
	private List<String> chartLabels = new LinkedList<String>();
	private List<BarData> chartData = new LinkedList<BarData>();
	private List<CustomLineData> mCustomLineDataset = new LinkedList<CustomLineData>();

	public BarChartView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}

	public BarChartView(Context context, AttributeSet attrs){
		super(context, attrs);
		initView();
	}

	public BarChartView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView()
	{
		chartLabels();
		chartDataSet();
		chartDesireLines();
		chartRender();

		//綁定手势滑动事件
		//this.bindTouch(this,chart);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		//图所占范围大小
		chart.setChartRange(w,h);
		secondChart.setChartRange(w,h);
	}


	private void chartRender()
	{
		try {

			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....
			/*int [] ltrb = getBarLnDefaultSpadding();
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);*/
			int [] ltrb = new int[5];
			ltrb[0] = DensityUtil.dip2px(getContext(), 60); //left
			ltrb[1] = DensityUtil.dip2px(getContext(), 40); //top
			ltrb[2] = DensityUtil.dip2px(getContext(), 60); //right
			ltrb[3] = DensityUtil.dip2px(getContext(), 40); //bottom
			ltrb[4] = DensityUtil.dip2px(getContext(), 66); //right
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);
			secondChart.setPadding(ltrb[0], ltrb[1], ltrb[4], ltrb[3]);

			//显示边框
			chart.showRoundBorder();

			//数据源
			chart.setDataSource(chartData);
			chart.setCategories(chartLabels);
			chart.setCustomLines(mCustomLineDataset);
			secondChart.setDataSource(null);
			secondChart.setCategories(chartLabels);

			//图例
			//chart.getAxisTitle().setLeftAxisTitle("参考成年男性标准值");
			//chart.getAxisTitle().setLowerAxisTitle("(请不要忽视您的健康)");

			//数据轴
			chart.getDataAxis().setAxisMax(15);
			chart.getDataAxis().setAxisMin(-5);
			chart.getDataAxis().setAxisSteps(5);
			secondChart.getDataAxis().setAxisMax(15);
			secondChart.getDataAxis().setAxisMin(-5);
			secondChart.getDataAxis().setAxisSteps(5);
			//指隔多少个轴刻度(即细刻度)后为主刻度
			chart.getDataAxis().setDetailModeSteps(1);
			secondChart.getDataAxis().setDetailModeSteps(1);

			secondChart.setDataAxisLocation(XEnum.AxisLocation.RIGHT);
			secondChart.getDataAxis().setHorizontalTickAlign(Align.RIGHT);

			chart.getDataAxis().enabledAxisStd();
			chart.getDataAxis().setAxisStd(0);
			secondChart.getDataAxis().enabledAxisStd();
			secondChart.getDataAxis().setAxisStd(0);
			chart.getCategoryAxis().setAxisBuildStd(true);
			secondChart.getCategoryAxis().setAxisBuildStd(true);


			chart.getDataAxis().getAxisPaint().setColor(Color.WHITE);
			chart.getDataAxis().getTickMarksPaint().setColor(Color.WHITE);
			secondChart.getDataAxis().getAxisPaint().setColor(Color.WHITE);
			secondChart.getDataAxis().getTickMarksPaint().setColor(Color.WHITE);
			//chart.getDataAxis().hideFirstTick();
			chart.getDataAxis().getTickMarksPaint().setStrokeWidth(1f);
			chart.getDataAxis().getAxisPaint().setStrokeWidth(1f);
			secondChart.getDataAxis().getTickMarksPaint().setStrokeWidth(1f);
			secondChart.getDataAxis().getAxisPaint().setStrokeWidth(1f);
			secondChart.getCategoryAxis().hide();
			secondChart.getDataAxis().hideTickMarks();
			chart.getCategoryAxis().getAxisPaint().setColor(Color.WHITE);
			chart.getCategoryAxis().getAxisPaint().setStrokeWidth(1f);
			chart.getCategoryAxis().hide();

			//定义数据轴标签显示格式
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){

				@Override
				public String textFormatter(String value) {
					if(value.equals("0.0")){
						chart.getDataAxis().getTickLabelPaint().setColor(0xFF5EBDC1);
						return "Deep";
					}else if(value.equals("5.0")){
						chart.getDataAxis().getTickLabelPaint().setColor(0xFFF8AC39);
						return "Light";
					}else if(value.equals("10.0")){
						chart.getDataAxis().getTickLabelPaint().setColor(0xFFEC3712);
						return "Wake";
					}else if(value.equals("-5.0")){
						chart.getDataAxis().getTickLabelPaint().setColor(0xFFFFFFFF);
						return "REM";
					}else {
						return " ";
					}
				}

			});
			secondChart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){

				@Override
				public String textFormatter(String s) {
					return " ";
				}
			});

			//标签旋转45度
			//	chart.getCategoryAxis().setTickLabelRotateAngle(45f);

			//在柱形顶部显示值
			chart.getBar().setItemLabelVisible(false);

			//隐藏Key
			chart.getPlotLegend().hide();

			//让柱子间没空白
			//chart.getBar().setBarInnerMargin(0f); //可尝试0.1或0.5各有啥效果噢
			chart.getBar().setBarTickSpacePercent(1.0f);
			chart.getBar().setBarStyle(XEnum.BarStyle.FILL);
			chart.getBar().setBarInnerMargin(0f);


			//禁用平移模式
			chart.disablePanMode();

			//chart.disableHighPrecision();

			//限制只能左右滑动
			//chart.setPlotPanMode(XEnum.PanMode.HORIZONTAL);

			//chart.setBarCenterStyle(XEnum.BarCenterStyle.TICKMARKS);

			// chart.showRoundBorder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void chartDataSet()
	{
		//标签对应的柱形数据集
		List<Double> dataSeriesA= new LinkedList<Double>();
		//依数据值确定对应的柱形颜色.
		List<Integer> dataColorA= new LinkedList<Integer>();

		int max = 20;
		int min = -5;

		for(int i=0;i<14;i++)
		{
			Random random = new Random();
			int v = random.nextInt(max)%(max-min+1) + min;
			dataSeriesA.add((double) v);

			if(v <= 0d ) {//REM
				dataColorA.add(Color.WHITE);
			}else if(v <= 5d){ //Deep
				dataColorA.add(Color.rgb(94, 189, 193));
			}else if(v <= 10d){ //Light
				dataColorA.add(Color.rgb(250, 171, 54));
			}else{  //Wake
				dataColorA.add(Color.rgb(236,53,17));
			}
		}
		//此地的颜色为Key值颜色及柱形的默认颜色
		BarData BarDataA = new BarData("",dataSeriesA,dataColorA,
				Color.rgb(53, 169, 239));

		chartData.add(BarDataA);
	}

	private void chartLabels()
	{
		for(int i=0;i<14;i++)
		{
			/*if(1 == i || i%5 == 0)
			{
				chartLabels.add(Integer.toString(i));
			}else{
				chartLabels.add("");
			}*/
			chartLabels.add("");
		}
	}

	/**
	 * 期望线/分界线
	 */
	private void chartDesireLines()
	{
		//mCustomLineDataset.add(new CustomLineData("REM",0d,Color.WHITE,2));
		mCustomLineDataset.add(new CustomLineData("",0d,Color.WHITE,1));
		mCustomLineDataset.add(new CustomLineData("",5d,Color.WHITE,1));
		mCustomLineDataset.add(new CustomLineData("",10d,Color.WHITE,1));

	}

	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

	@Override
	public void render(Canvas canvas) {
		try{
			chart.render(canvas);
			secondChart.render(canvas);


			paint.setTextSize(22);
			paint.setColor(Color.WHITE);

			float textHeight = DrawHelper.getInstance().getPaintFontHeight(paint);
			paint.setTextAlign(Align.LEFT);
			canvas.drawText("6pm",chart.getPlotArea().getLeft(), chart.getPlotArea().getTop() - textHeight ,paint);
			canvas.drawText("8pm",secondChart.getPlotArea().getRight(), chart.getPlotArea().getTop() - textHeight ,paint);

			paint.setTextAlign(Align.RIGHT);
			//canvas.drawText("8pm",chart.getPlotArea().getRight(), chart.getPlotArea().getBottom() + textHeight ,paint);

		} catch (Exception e){
			Log.e(TAG, e.toString());
		}
	}

}
