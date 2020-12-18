package com.example.practicaexamen.ui.gallery;

import android.app.Notification;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.AnyColor;
import com.anychart.graphics.vector.ColoredFill;
import com.anychart.palettes.RangeColors;
import com.example.practicaexamen.Data.AdminDB;
import static com.example.practicaexamen.Gestion.MedidasGestion.getMasas;
import static com.example.practicaexamen.Gestion.MedidasGestion.getObesidad;
import static com.example.practicaexamen.Gestion.MedidasGestion.getPesos;
import com.example.practicaexamen.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        //Column Chart --- Pesos
        final AnyChartView Barchart = root.findViewById(R.id.BarChart);
        Barchart.setProgressBar(root.findViewById(R.id.progress_bar));
        APIlib.getInstance().setActiveAnyChartView(Barchart);
        Cartesian cartesian = AnyChart.column();
        Column column = cartesian.column(getPesos());

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Historial de Peso");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Fechas");
        cartesian.yAxis(0).title("Peso (kg)");
        RangeColors palette = RangeColors.instantiate();
        palette.items("#d45b67", "#b01a29");
        palette.count(10);
        cartesian.palette(palette);
        Barchart.setChart(cartesian);

        // Pie Chart --- Masas
        final AnyChartView piechart = root.findViewById(R.id.PieChart);
        APIlib.getInstance().setActiveAnyChartView(piechart);
        final Pie pie = AnyChart.pie();
        pie.title("Análisis Múscular");
        pie.data(getMasas());
        pie.animation(true);
        pie.palette().items("#d45b67", "#b01a29");
        piechart.setChart(pie);

        // Pie Chart --- Obesidad
        final AnyChartView piechart2 = root.findViewById(R.id.PieChart2);
        APIlib.getInstance().setActiveAnyChartView(piechart2);
        final Pie pie2 = AnyChart.pie();
        pie2.title("Análisis de Obesidad");
        pie2.palette().items("#d45b67", "#b01a29");
        pie2.data(getObesidad());
        pie2.animation(true);
        piechart2.setChart(pie2);

        return root;
    }
}