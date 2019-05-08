package aplicacion.android.app.betusto.fruterinapoles;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_Inventario extends AppCompatActivity {

    private PieChart pieChart1, pieChart2, pieChart3, pieChart4, pieChart5, pieChart6, pieChart7, pieChart8, pieChart9, pieChart10, pieChart11, pieChart12, pieChart13, pieChart14,
            pieChart15, pieChart16;
    private DatabaseReference Database;
    private boolean noMasDataChanges = false;
    private List CantidadDeSnapshots = new ArrayList();

    private List CebollaPrimeraBlanca = new ArrayList();
    private List CebollaPrimeraBlancaColors = new ArrayList();
    int cebollablancaprimera = 0;
    private List CebollaSegundaBlanca = new ArrayList();
    private List CebollaSegundaBlancaColors = new ArrayList();
    int cebollasegundablanca = 0;
    private List CebollaAmarilla = new ArrayList();
    private List CebollaAmarillaColors = new ArrayList();
    int cebollaamarillaa = 0;
    private List LimonList = new ArrayList();
    private List LimonListColors = new ArrayList();
    int limonlist = 0;
    private List JalapenoList = new ArrayList();
    private List JalapenoListColors = new ArrayList();
    int jalapenolist = 0;
    private List PapaList = new ArrayList();
    private List PapaListColors = new ArrayList();
    int papalist = 0;
    private List AguacateList = new ArrayList();
    private List AguacateListColors = new ArrayList();
    int aguacatelist = 0;
    private List TomateList = new ArrayList();
    private List TomateListColors = new ArrayList();
    int tomatelist = 0;
    private List CilantroList = new ArrayList();
    private List CilantroListColors = new ArrayList();
    int cilantrolist = 0;
    private List LechugaList = new ArrayList();
    private List LechugaListColors = new ArrayList();
    int lechugalist = 0;
    private List GaleanaList = new ArrayList();
    private List GaleanaListColors = new ArrayList();
    int galeanalist = 0;
    private List MangoList = new ArrayList();
    private List MangoListColors = new ArrayList();
    int mangolist = 0;
    private List PlanatnoList = new ArrayList();
    private List PlanatnoListColors = new ArrayList();
    int platanolist = 0;
    private List ManzanaList = new ArrayList();
    private List ManzanaListColors = new ArrayList();
    int manzanalist = 0;
    private List ChileArbolList = new ArrayList();
    private List ChileArbolListColors = new ArrayList();
    int chilearbollist = 0;
    private List ChileJaponesList = new ArrayList();
    private List ChileJaponesListColors = new ArrayList();
    int chilejaponeslist = 0;

    ArrayList<Integer> colorsss = new ArrayList<Integer>();
    SharedPreferences sharedPreferences;
    VariablesEstaticas VE = new VariablesEstaticas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__inventario);
        pieChart1 = findViewById(R.id.cebollablancaprimera);
        pieChart2 = findViewById(R.id.cebollablancasegunda);
        pieChart3 = findViewById(R.id.cebollaamarilla);
        pieChart4 = findViewById(R.id.limon);
        pieChart5 = findViewById(R.id.jalapeno);
        pieChart6 = findViewById(R.id.papa);
        pieChart7 = findViewById(R.id.aguacate);
        pieChart8 = findViewById(R.id.tomate);
        pieChart9 = findViewById(R.id.cilantro);
        pieChart10 = findViewById(R.id.lechuga);
        pieChart11 = findViewById(R.id.galeana);
        pieChart12 = findViewById(R.id.mango);
        pieChart13 = findViewById(R.id.platano);
        pieChart14 = findViewById(R.id.manzana);
        pieChart15 = findViewById(R.id.chilejapones);
        pieChart16 = findViewById(R.id.chilearbol);

        Database = FirebaseDatabase.getInstance().getReference("Entradas");

        Database.keepSynced(true);
        sharedPreferences = getSharedPreferences(VariablesEstaticas.SHARED_PREFS, Context.MODE_PRIVATE);
        VE.CargarDatos(sharedPreferences);
        /*ArrayList NoOfEmp = new ArrayList();
        NoOfEmp.add(new Entry(945, 0));
        NoOfEmp.add(new Entry(1040, 1));
        NoOfEmp.add(new Entry(1133f, 2));
        NoOfEmp.add(new Entry(1240f, 3));
        NoOfEmp.add(new Entry(1369f, 4));
        NoOfEmp.add(new Entry(1487f, 5));
        NoOfEmp.add(new Entry(1501f, 6));
        NoOfEmp.add(new Entry(1645f, 7));
        NoOfEmp.add(new Entry(1578f, 8));
        NoOfEmp.add(new Entry(1695f, 9));
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
        ArrayList year = new ArrayList();
        pieChart1.setDescription("Cebolla");
        year.add("");
        year.add("Stock");
        year.add("2010");
        year.add("2011");
        year.add("2012");
        year.add("2013");
        year.add("2014");
        year.add("2015");
        year.add("2016");
        year.add("2017");
        PieData data = new PieData(year, dataSet);
        pieChart1.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart1.animateXY(1000, 1000);*/
        ArrayList NoOfEmp = new ArrayList();
        NoOfEmp.add(new Entry(1, 0));
        PieDataSet dataSet = new PieDataSet(NoOfEmp, "");
        ArrayList year = new ArrayList();
        year.add("Disp.");
        pieChart1.setDescription("Cebolla Blanca. Prim.");
        pieChart2.setDescription("Cebolla Blanca. Seg.");
        pieChart3.setDescription("Cebolla Blanca Amarilla");
        pieChart5.setDescription("Jalapeño Kilos");
        pieChart4.setDescription("Limon. Bulto.");
        pieChart6.setDescription("Papa Bulto.");
        pieChart7.setDescription("Aguacate Caja");
        pieChart8.setDescription("Tomate Caja");
        pieChart9.setDescription("Cilantro Caja");
        pieChart10.setDescription("Lechuga Piezas");
        pieChart11.setDescription("Galeana Bulto");
        pieChart12.setDescription("Mango. Kilos.");
        pieChart13.setDescription("Plátano. Kilos.");
        pieChart14.setDescription("Manzana. Kilos.");
        pieChart15.setDescription("Chile Japonés. Kilos.");
        pieChart16.setDescription("Chile Árbol. Kilos.");
        PieData data = new PieData(year, dataSet);
        pieChart1.setData(data);
        pieChart2.setData(data);
        pieChart3.setData(data);
        pieChart4.setData(data);
        pieChart5.setData(data);
        pieChart6.setData(data);
        pieChart7.setData(data);
        pieChart8.setData(data);
        pieChart9.setData(data);
        pieChart10.setData(data);
        pieChart11.setData(data);
        pieChart12.setData(data);
        pieChart13.setData(data);
        pieChart14.setData(data);
        pieChart15.setData(data);
        pieChart16.setData(data);

        colorsss.add(Color.rgb(190, 190, 190));
        dataSet.setColors(colorsss);
        pieChart1.animateXY(1000, 1000);
        pieChart2.animateXY(1000, 1000);
        pieChart3.animateXY(1000, 1000);
        pieChart4.animateXY(1000, 1000);
        pieChart5.animateXY(1000, 1000);
        pieChart6.animateXY(1000, 1000);
        pieChart7.animateXY(1000, 1000);
        pieChart8.animateXY(1000, 1000);
        pieChart9.animateXY(1000, 1000);
        pieChart10.animateXY(1000, 1000);

        pieChart11.animateXY(1000, 1000);
        pieChart12.animateXY(1000, 1000);
        pieChart13.animateXY(1000, 1000);
        pieChart14.animateXY(1000, 1000);
        pieChart15.animateXY(1000, 1000);
        pieChart16.animateXY(1000, 1000);



        LecturaBaseDeDatos();
    }

    public void CebollaBlancaPrimera (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart1.setDescription("Cebolla Blanca. Prim.");
        PieData data = new PieData(Year, dataSet);
        pieChart1.setData(data);
        dataSet.setColors(colors);
        pieChart1.animateXY(1000, 1000);
    }

    public void CebollaBlancaSegunda (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart2.setDescription("Cebolla Blanca. Seg.");
        PieData data = new PieData(Year, dataSet);
        pieChart2.setData(data);
        dataSet.setColors(colors);
        pieChart2.animateXY(1000, 1000);
    }
    public void CebollaAAmarilla (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart3.setDescription("Cebolla Blanca Amarilla");
        PieData data = new PieData(Year, dataSet);
        pieChart3.setData(data);
        dataSet.setColors(colors);
        pieChart3.animateXY(1000, 1000);
    }
    public void Limon_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart4.setDescription("Limon. Bulto.");
        PieData data = new PieData(Year, dataSet);
        pieChart4.setData(data);
        dataSet.setColors(colors);
        pieChart4.animateXY(1000, 1000);
    }
    public void Jalapeno_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart5.setDescription("Jalapeño Kilos");
        PieData data = new PieData(Year, dataSet);
        pieChart5.setData(data);
        dataSet.setColors(colors);
        pieChart5.animateXY(1000, 1000);
    }
    public void Papa_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart6.setDescription("Papa Bulto.");
        PieData data = new PieData(Year, dataSet);
        pieChart6.setData(data);
        dataSet.setColors(colors);
        pieChart6.animateXY(1000, 1000);
    }
    public void Aguacate_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart7.setDescription("Aguacate Caja");
        PieData data = new PieData(Year, dataSet);
        pieChart7.setData(data);
        dataSet.setColors(colors);
        pieChart7.animateXY(1000, 1000);
    }
    public void Tomate_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart8.setDescription("Tomate Caja");
        PieData data = new PieData(Year, dataSet);
        pieChart8.setData(data);
        dataSet.setColors(colors);
        pieChart8.animateXY(1000, 1000);
    }
    public void Cilantro_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart9.setDescription("Cilantro Caja");
        PieData data = new PieData(Year, dataSet);
        pieChart9.setData(data);
        dataSet.setColors(colors);
        pieChart9.animateXY(1000, 1000);
    }
    public void Lechuga_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart10.setDescription("Lechuga Piezas");
        PieData data = new PieData(Year, dataSet);
        pieChart10.setData(data);
        dataSet.setColors(colors);
        pieChart10.animateXY(1000, 1000);
    }
    public void Galeana_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart11.setDescription("Galeana Bulto");
        PieData data = new PieData(Year, dataSet);
        pieChart11.setData(data);
        dataSet.setColors(colors);
        pieChart11.animateXY(1000, 1000);
    }
    public void Mango_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart12.setDescription("Mango. Kilos.");
        PieData data = new PieData(Year, dataSet);
        pieChart12.setData(data);
        dataSet.setColors(colors);
        pieChart12.animateXY(1000, 1000);
    }
    public void Platano_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart13.setDescription("Plátano. Kilos.");
        PieData data = new PieData(Year, dataSet);
        pieChart13.setData(data);
        dataSet.setColors(colors);
        pieChart13.animateXY(1000, 1000);
    }
    public void Manzana_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart14.setDescription("Manzana. Kilos.");
        PieData data = new PieData(Year, dataSet);
        pieChart14.setData(data);
        dataSet.setColors(colors);
        pieChart14.animateXY(1000, 1000);
    }
    public void ChileJapones_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart15.setDescription("Chile Japonés. Kilos.");
        PieData data = new PieData(Year, dataSet);
        pieChart15.setData(data);
        dataSet.setColors(colors);
        pieChart15.animateXY(1000, 1000);
    }
    public void ChileArbol_ (ArrayList Productos, ArrayList Year, ArrayList colors){
        PieDataSet dataSet = new PieDataSet(Productos, "");
        pieChart16.setDescription("Chile Árbol. Kilos.");
        PieData data = new PieData(Year, dataSet);
        pieChart16.setData(data);
        dataSet.setColors(colors);
        pieChart16.animateXY(1000, 1000);
    }



    @Override
    protected void onStart() {
        super.onStart();
    }

    private void LecturaBaseDeDatos() {
        Database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final int CantidadTotalDeSnaps = (int) dataSnapshot.getChildrenCount();
                if (dataSnapshot.getValue() != null) { //*No se puede usar equals en null aunque sea string*
                    for (final DataSnapshot snapshot : dataSnapshot.getChildren()) //Recorremos cada campo de la tabla Usuarios
                    {
                        Database.child(snapshot.getKey()).child("NombreProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                final String datochido = dataSnapshot.getValue().toString();
                                if (datochido.equals("Cebolla Blanca Primera")) {

                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    CebollaPrimeraBlanca.add(num1);
                                                    CebollaPrimeraBlancaColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (cebollablancaprimera ==0 ){
                                                        int resultado = 8 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < CebollaPrimeraBlanca.size(); j++){
                                                            int c = (int) CebollaPrimeraBlanca.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (CebollaPrimeraBlancaColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (CebollaPrimeraBlancaColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (CebollaPrimeraBlancaColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 8 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, CebollaPrimeraBlanca.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    cebollablancaprimera++;
                                                    CebollaBlancaPrimera(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Cebolla Blanca Segunda")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    CebollaSegundaBlanca.add(num1);
                                                    CebollaSegundaBlancaColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (cebollasegundablanca ==0 ){
                                                        int resultado = 20 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < CebollaSegundaBlanca.size(); j++){
                                                            int c = (int) CebollaSegundaBlanca.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (CebollaSegundaBlancaColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (CebollaSegundaBlancaColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (CebollaSegundaBlancaColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 20 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, CebollaSegundaBlanca.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    cebollasegundablanca++;
                                                    CebollaBlancaSegunda(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Cebolla Amarilla")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    CebollaAmarilla.add(num1);
                                                    CebollaAmarillaColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (cebollaamarillaa ==0 ){
                                                        int resultado = 20 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < CebollaAmarilla.size(); j++){
                                                            int c = (int) CebollaAmarilla.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (CebollaAmarillaColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (CebollaAmarillaColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (CebollaAmarillaColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 20 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, CebollaAmarilla.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    cebollaamarillaa++;
                                                    CebollaAAmarilla(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Limon")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    LimonList.add(num1);
                                                    LimonListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (limonlist ==0 ){
                                                        int resultado = 20 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < LimonList.size(); j++){
                                                            int c = (int) LimonList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (LimonListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (LimonListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (LimonListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 20 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, LimonList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    limonlist++;
                                                    Limon_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Jalapeno")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    JalapenoList.add(num1);
                                                    JalapenoListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (jalapenolist ==0 ){
                                                        int resultado = 248 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < JalapenoList.size(); j++){
                                                            int c = (int) JalapenoList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (JalapenoListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (JalapenoListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (JalapenoListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 248 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, JalapenoList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    jalapenolist++;
                                                    Jalapeno_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Papa")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    PapaList.add(num1);
                                                    PapaListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (papalist ==0 ){
                                                        int resultado = 15 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < PapaList.size(); j++){
                                                            int c = (int) PapaList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (PapaListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (PapaListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (PapaListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 15 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, PapaList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    papalist++;
                                                    Papa_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Aguacate")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    AguacateList.add(num1);
                                                    AguacateListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (aguacatelist ==0 ){
                                                        int resultado = 10 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < AguacateList.size(); j++){
                                                            int c = (int) AguacateList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (AguacateListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (AguacateListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (AguacateListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 10 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, AguacateList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    aguacatelist++;
                                                    Aguacate_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Tomate")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    TomateList.add(num1);
                                                    TomateListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (tomatelist ==0 ){
                                                        int resultado = 20 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < TomateList.size(); j++){
                                                            int c = (int) TomateList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (TomateListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (TomateListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (TomateListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 20 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, TomateList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    tomatelist++;
                                                    Tomate_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Cilantro")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    CilantroList.add(num1);
                                                    CilantroListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (cilantrolist ==0 ){
                                                        int resultado = 7 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < CilantroList.size(); j++){
                                                            int c = (int) CilantroList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (CilantroListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (CilantroListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (CilantroListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 7 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, CilantroList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    cilantrolist++;
                                                    Cilantro_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Lechuga")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    LechugaList.add(num1);
                                                    LechugaListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (lechugalist ==0 ){
                                                        int resultado = 72 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < LechugaList.size(); j++){
                                                            int c = (int) LechugaList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (LechugaListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (LechugaListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (LechugaListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 72 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, LechugaList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    lechugalist++;
                                                    Lechuga_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Papa Galeana")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    GaleanaList.add(num1);
                                                    GaleanaListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (galeanalist ==0 ){
                                                        int resultado = 16 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < GaleanaList.size(); j++){
                                                            int c = (int) GaleanaList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (GaleanaListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (GaleanaListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (GaleanaListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 16 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, GaleanaList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    galeanalist++;
                                                    Galeana_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Mango")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    MangoList.add(num1);
                                                    MangoListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (mangolist ==0 ){
                                                        int resultado = 60 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < MangoList.size(); j++){
                                                            int c = (int) MangoList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (MangoListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (MangoListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (MangoListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 248 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, MangoList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    mangolist++;
                                                    Mango_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Platano")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    PlanatnoList.add(num1);
                                                    PlanatnoListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (platanolist ==0 ){
                                                        int resultado = 60 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < PlanatnoList.size(); j++){
                                                            int c = (int) PlanatnoList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (PlanatnoListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (PlanatnoListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (PlanatnoListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 60 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, PlanatnoList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    platanolist++;
                                                    Platano_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Chile Japones")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    ChileJaponesList.add(num1);
                                                    ChileJaponesListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (chilejaponeslist ==0 ){
                                                        int resultado = 6 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < ChileJaponesList.size(); j++){
                                                            int c = (int) ChileJaponesList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (ChileJaponesListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (ChileJaponesListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (ChileJaponesList.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 6 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, ChileJaponesList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    chilejaponeslist++;
                                                    ChileJapones_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Manzana")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    ManzanaList.add(num1);
                                                    ManzanaListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (manzanalist ==0 ){
                                                        int resultado = 60 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < ManzanaList.size(); j++){
                                                            int c = (int) ManzanaList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (ManzanaListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (ManzanaListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (ManzanaListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 60 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, ManzanaList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    manzanalist++;
                                                    Manzana_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else if (datochido.equals("Chile de Arbol")) {
                                    Database.child(snapshot.getKey()).child("CantidadProducto").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            final String Cantidad1 = dataSnapshot.getValue().toString();
                                            Database.child(snapshot.getKey()).child("GradoMadurez").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String Madurez = dataSnapshot.getValue().toString();
                                                    int num1 = Integer.parseInt(Cantidad1);
                                                    ChileArbolList.add(num1);
                                                    ChileArbolListColors.add(Madurez);
                                                    ArrayList NoOfEmp = new ArrayList();
                                                    ArrayList year = new ArrayList();
                                                    ArrayList<Integer> colors = new ArrayList<Integer>();
                                                    int resultado1 = 0;
                                                    if (chilearbollist ==0 ){
                                                        int resultado = 20 - num1;
                                                        int aux1 = Math.abs(resultado);
                                                        NoOfEmp.add(new Entry(num1, 0));
                                                        NoOfEmp.add(new Entry(aux1, 1));
                                                        year.add("Usado");
                                                        if (Madurez.equals("Red")) {
                                                            colors.add(Color.rgb(194, 59, 34));

                                                        } else if (Madurez.equals("Yellow")) {
                                                            colors.add(Color.rgb(251, 227, 55));

                                                        } else if (Madurez.equals("Green")) {
                                                            colors.add(Color.rgb(119, 221, 119));

                                                        }
                                                    } else {
                                                        for (int j = 0; j < ChileArbolList.size(); j++){
                                                            int c = (int) ChileArbolList.get(j);
                                                            NoOfEmp.add(new Entry(c, j));
                                                            resultado1 = resultado1 + c;
                                                            c = 0;
                                                            year.add("Usado");
                                                            if (ChileArbolListColors.get(j).equals("Red")) {
                                                                colors.add(Color.rgb(194, 59, 34));

                                                            } else if (ChileArbolListColors.get(j).equals("Yellow")) {
                                                                colors.add(Color.rgb(251, 227, 55));

                                                            } else if (ChileArbolListColors.get(j).equals("Green")) {
                                                                colors.add(Color.rgb(119, 221, 119));

                                                            }
                                                        }
                                                        int aux = 20 - resultado1;
                                                        int aux1 = Math.abs(aux);
                                                        NoOfEmp.add(new Entry(aux1, ChileArbolList.size()));
                                                    }
                                                    colors.add(Color.rgb(190, 190, 190));
                                                    year.add("Disp.");
                                                    chilearbollist++;
                                                    ChileArbol_(NoOfEmp, year, colors);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(Activity_Inventario.this, "Chale", Toast.LENGTH_SHORT).show();
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                //Toast.makeText(RegisterActivity.this, "Ocurrio un error al intentar acceder a la base de datos", Toast.LENGTH_SHORT).show();
                            }
                        });
                        //Si no hay ningun usuario en la base de datos
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Activity_Inventario.this, "Ocurrio un error al intentar acceder a la base de datos", Toast.LENGTH_SHORT).show();
            }
        });


    }

    //Revisar conexion internet
    @Override
    protected void onResume() {
        //Metodo para revisar merma y grado de madurez
        BaseDeDatos BD = new BaseDeDatos();
        BD.RevisarCada15SegundosEstadoGrados(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        //Detener deteccion de merma y grado de madurez
        BaseDeDatos BD = new BaseDeDatos();
        BD.DetenerContadorMerma();
        super.onPause();
    }

}