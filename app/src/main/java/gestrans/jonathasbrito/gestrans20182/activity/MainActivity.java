package gestrans.jonathasbrito.gestrans20182.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import gestrans.jonathasbrito.gestrans20182.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gráfico
        LineChart chart = (LineChart)findViewById(R.id.chart);
/**
 List<Entry> entries = new ArrayList<Entry>();
 entries.add(new Entry(1, 10f));
 entries.add(new Entry(2, 75f));
 entries.add(new Entry(3, 50f));
 entries.add(new Entry(4, 100f));
 entries.add(new Entry(5, 200f));
 **/
        ArrayList<Entry> dados1 = new ArrayList<>();
        ArrayList<Entry> dados2 = new ArrayList<>();

        dados1.add(new Entry(1, 30f));
        dados1.add(new Entry(2, 75f));
        dados1.add(new Entry(3, 70f));
        dados1.add(new Entry(4, 20f));
        dados1.add(new Entry(5, 200f));

        dados2.add(new Entry(1, 10f));
        dados2.add(new Entry(2, 75f));
        dados2.add(new Entry(3, 50f));
        dados2.add(new Entry(4, 100f));
        dados2.add(new Entry(5, 200f));

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(dados1, "Linha 1");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(dados2, "Linha 2");
        lineDataSet2.setDrawCircles(false);
        lineDataSet1.setColor(Color.RED);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        chart.setData(new LineData(lineDataSets));

/**
 XAxis xAxis = chart.getXAxis();
 xAxis.setValueFormatter(new MyXAxisValueFormatter());
 //Evita a duplicação dos meses na linha X
 xAxis.setGranularity(1f);

 YAxis yAxis = chart.getAxisLeft();
 yAxis.setValueFormatter(new MyYAxisValueFormatter());


 LineDataSet dataSet = new LineDataSet(entries, "Vendas");
 dataSet.setDrawFilled(true);
 dataSet.setDrawValues(false);
 LineData lineData = new LineData(dataSet);
 chart.setData(lineData);

 //Desativa o zoom do touch
 chart.setDoubleTapToZoomEnabled(false);

 //listItemView
 //listItemView = (ListView)findViewById(R.id.listView1);


 //Adaptador
 //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, listItemsValue);

 //listItemView.setAdapter(adapter);
 **/

        //Listener para exibir toast ao tocar no gráfico
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(MainActivity.this, "R$" + e.getY(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        //efeito animação
        chart.animateXY(3000, 3000);
        chart.invalidate();


    }

    public void proximaTela2(View view){

        Intent intent = new Intent(this, CorridasActivity.class);
        startActivity(intent);
    }

    public void irReceitas(View view){

        Intent intent = new Intent (this, ReceitasActivity.class);
        startActivity(intent);

    }

    public void irDespesas(View view){

        Intent intent = new Intent (this, DespesasActivity.class);
        startActivity(intent);

    }

    //Formata os valores na linha X
    public class MyXAxisValueFormatter implements IAxisValueFormatter{

        private String[] mValues = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho"};

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Log.d("Teste", mValues[(int)value]);
            return mValues[(int)value];
        }
    }

    //Formata os valores na linha Y
    public class MyYAxisValueFormatter implements IAxisValueFormatter {

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return "R$ " + value;
        }
    }


}
