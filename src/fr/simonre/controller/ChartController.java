package fr.simonre.controller;

import java.util.Map;

import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

import fr.simonre.view.ChartView;

public final class ChartController 
{
	private static volatile ChartController instance = null;
	
	ChartView cv;
	public XYChart chart;
	
	 public void showChartView(String chart_name, Map<String, int[]> results)
	 {
	    XYChart chart = new XYChartBuilder().width(800).height(600).title(chart_name).xAxisTitle("X").yAxisTitle("Y").build();
	 
	    chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
	    chart.getStyler().setAxisTitlesVisible(false);
	    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Line);
	    
	    for (Map.Entry<String, int[]> entry : results.entrySet()) 
	    {
	        chart.addSeries(entry.getKey(), entry.getValue());
	    }
	    
	    cv = new ChartView(chart);
	 }
	
	//SINGLETON
	public final static ChartController getInstance() 
	{
        if (ChartController.instance == null) 
        {
           synchronized(ChartController.class) 
           {
             if (ChartController.instance == null)
            	 ChartController.instance = new ChartController();
           }
        }
        return ChartController.instance;
    }
}
