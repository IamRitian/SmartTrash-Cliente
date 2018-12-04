package com.example.acceso.smarttrash_cliente;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Estadisticas extends Fragment {


    public Estadisticas() {
        // Required empty public constructor
    }

    private PieChart pieChart;
    private BarChart barChart;
    private String[]months; //=new String[]{"Organico","Inorganico","Sanitario"};
    private int[]points ; //= new int[]{25, 30, 38};
    private int[]colors; //= new int[]{Color.rgb(27, 94, 32), Color.rgb(46, 125, 50), Color.rgb(130, 119, 23)};
//, Color.rgb(158, 157, 36), Color.rgb(214, 191, 10), Color.rgb(255, 222, 38), Color.rgb(249, 168, 37), Color.rgb(245, 127, 23), Color.rgb(216, 67, 21), Color.rgb(191, 54, 12), Color.rgb(141, 66, 18), Color.rgb(77, 82, 26)


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        months = new String[]{"Organico","Inorganico","Sanitario"};
        points = new int[]{25, 30, 38};
        colors = new int[]{Color.rgb(27, 94, 32), Color.rgb(46, 125, 50), Color.rgb(130, 119, 23)};

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);
        barChart=(BarChart)view.findViewById(R.id.barChart);
        pieChart=(PieChart)view.findViewById(R.id.pieChart);

        createCharts();
        return view;


    }
    private Chart getSameChart(Chart chart, String description, int textColor, int background, int animateY){
        chart.getDescription().setText("");
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend(chart);
        return chart;
    }

    private void legend(Chart chart){
        Legend legend=chart.getLegend();
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setFormSize(6);

        ArrayList<LegendEntry> entries=new ArrayList<>();
        for(int i=0;i<months.length;i++){
            LegendEntry entry=new LegendEntry();
            entry.formColor=colors[i];
            entry.label=months[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }

    private ArrayList<BarEntry>getBarEntries(){
        ArrayList<BarEntry>entries=new ArrayList<>();
        for(int i=0;i<points.length;i++)
            entries.add(new BarEntry(i,points[i]));
        return entries;
    }

    private ArrayList<PieEntry>getPieEntries(){
        ArrayList<PieEntry>entries=new ArrayList<>();
        for(int i=0;i<points.length;i++)
            entries.add(new PieEntry(points[i]));
        return entries;
    }

    private void axisX(XAxis axis){
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(months));
    }

    private void axisLeft(YAxis axis){
        axis.setSpaceTop(10);
        axis.setAxisMinimum(0);
    }

    private void axisRight(YAxis axis){
        axis.setEnabled(false);
    }

    private void createCharts(){
        barChart=(BarChart)getSameChart(barChart, "Series", Color.BLACK, Color.WHITE, 2000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData());
        barChart.invalidate();
        barChart.getLegend().setEnabled(false);

        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());

        pieChart=(PieChart)getSameChart(pieChart, "Puntos", Color.GRAY, Color.WHITE, 2000);
        pieChart.setHoleRadius(40);
        pieChart.setTransparentCircleRadius(12);
        //pieChart.setDrawHoleEnabled(false);
        pieChart.setData(getPieData());
        pieChart.invalidate();
        pieChart.getLegend().setTextSize(10);
    }

    private DataSet getData(DataSet dataSet){
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(6);
        return dataSet;
    }

    private BarData getBarData(){
        BarDataSet barDataSet=(BarDataSet)getData(new BarDataSet(getBarEntries(),""));

        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData=new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private PieData getPieData(){
        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(),""));

        pieDataSet.setSliceSpace(1);
        pieDataSet.setValueFormatter(new PercentFormatter());

        return new PieData(pieDataSet);
    }

}
