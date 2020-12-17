package com.example.practicaexamen.ui.gallery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.example.practicaexamen.Data.AdminDB;
import static com.example.practicaexamen.Gestion.MedidasGestion.getMasas;
import static com.example.practicaexamen.Gestion.MedidasGestion.getPesos;
import com.example.practicaexamen.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private static AdminDB data=null;  //Enlace con la base de datos fisica
    private static SQLiteDatabase conexion=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

       conexion=data.getReadableDatabase();
       Cursor datos=conexion.rawQuery("select * from Fitness",
                null);

        //Column Chart --- Pesos
        AnyChartView anyChartView = root.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(root.findViewById(R.id.progress_bar));


        Cartesian cartesian = AnyChart.column();

        /*ArrayList<DataEntry> listaPesos = new ArrayList<>();
        while (datos.moveToNext()) {
            listaPesos.add(
                    new ValueDataEntry(
                            datos.getString(1),
                            ((int)datos.getDouble(2))
                    ));
        }*/

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

        anyChartView.setChart(cartesian);

        // Pie Chart --- Masas
        Pie pie = AnyChart.pie();

        /*ArrayList<DataEntry> Masas = new ArrayList<>();
        while (datos.moveToLast()) {
            Masas.add(
                    new ValueDataEntry(
                            "Masa Muscular",
                            ((int)datos.getDouble(3))
                    ));
            Masas.add(
                    new ValueDataEntry(
                            "Masa Grasa",
                            ((int)datos.getDouble(4))
                    ));
        }*/

        pie.data(getMasas());

        AnyChartView piechart = (AnyChartView) root.findViewById(R.id.PieChart);
        piechart.setChart(pie);
        return root;
    }
}