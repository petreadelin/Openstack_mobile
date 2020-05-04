package com.petrique.openstack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class NetworksFragment extends Fragment {

    AnyChartView anyChartView;

    String[] subnets = {"192.168.0.0", "172.16.10.82", "10.0.0.0"};
    int[] used = {3, 5, 12};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_networks, container, false);

        anyChartView = rootView.findViewById(R.id.chartView);
        setupPieChart();

        return rootView;
    }

    public void setupPieChart() {

        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for (int i = 0; i < subnets.length; i++) {
            dataEntries.add(new ValueDataEntry(subnets[i], used[i]));
        }

        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }
}
