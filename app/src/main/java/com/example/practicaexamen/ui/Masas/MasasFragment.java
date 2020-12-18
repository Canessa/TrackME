package com.example.practicaexamen.ui.Masas;

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
import com.anychart.core.cartesian.series.Line;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.palettes.RangeColors;
import com.example.practicaexamen.Data.AdminDB;
import static com.example.practicaexamen.Gestion.MedidasGestion.getMasas;
import static com.example.practicaexamen.Gestion.MedidasGestion.getPesos;
import com.example.practicaexamen.R;

import java.util.ArrayList;

public class MasasFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_masas, container, false);

        //Column Chart --- Pesos
        final AnyChartView Linechart = root.findViewById(R.id.LineChart);
        Linechart.setProgressBar(root.findViewById(R.id.progress_bar));
        APIlib.getInstance().setActiveAnyChartView(Linechart);
        Cartesian cartesian = AnyChart.line();
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
        Linechart.setChart(cartesian);
        return root;
    }
}
