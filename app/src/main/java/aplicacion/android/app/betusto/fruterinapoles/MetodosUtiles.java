package aplicacion.android.app.betusto.fruterinapoles;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import static android.content.Context.MODE_PRIVATE;

public class MetodosUtiles {

    //Elimina emojis
    public InputFilter filters(){
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    int type = Character.getType(source.charAt(i));
                    //System.out.println("Type : " + type);
                    if (type == Character.SURROGATE || type == Character.OTHER_SYMBOL) {
                        return "";
                    }
                }
                return null;
            }
        };
        return filter;
    }

    //Clase encargada de que los edittexts puedan tener diferentes rangos de valores
    public static class InputFilterMinMax implements InputFilter {
        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }



    //Metodo para subrayar un textview por medio segundo
    public void Subrayar(final TextView text){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                text.setText(text.getResources().getString(R.string.No_Subrayado)); //termina de subrayar
            }
        }, 500);
    }


    private Toast ToastMessage;
    public void MostrarToast(Context context, String Texto){
        //Para no repetir toasts
        if (ToastMessage != null) {
            ToastMessage.cancel();
        }
        ToastMessage = Toast.makeText(context, Texto, Toast.LENGTH_LONG);
        ToastMessage.show();
    }

    private boolean estaVisible = false;
    public void OjosContraseñas(EditText contraseñaTexto, ImageButton VisibilidadOjos){
        if(estaVisible){ //Los caracteres se veran
            contraseñaTexto.setTransformationMethod(PasswordTransformationMethod.getInstance());
            contraseñaTexto.setSelection(contraseñaTexto.getText().length()); //Necesario para que el cursor se ponga hasta el final del texto
            VisibilidadOjos.setImageResource(R.mipmap.ic_notvisible); //Agregamos la imagen
            estaVisible = false; //La proxima vez que se le oprima hará el efecto contrario
        }else{ //Los caracteres permaneceran ocultos
            contraseñaTexto.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            contraseñaTexto.setSelection(contraseñaTexto.getText().length()); //Necesario para que el cursor se ponga hasta el final del texto
            VisibilidadOjos.setImageResource(R.mipmap.ic_visible); //Agregamos la imagen
            estaVisible = true; //La proxima vez que se le oprima hará el efecto contrario
        }
    }

    public void OjosContraseñasOnResume(EditText contraseñaTexto, ImageButton VisibilidadOjos){
        if(estaVisible){
            contraseñaTexto.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            contraseñaTexto.setSelection(contraseñaTexto.getText().length()); //Necesario para que el cursor se ponga hasta el final del texto
            VisibilidadOjos.setImageResource(R.mipmap.ic_visible); //Agregamos la imagen
        }else{
            contraseñaTexto.setTransformationMethod(PasswordTransformationMethod.getInstance());
            contraseñaTexto.setSelection(contraseñaTexto.getText().length()); //Necesario para que el cursor se ponga hasta el final del texto
            VisibilidadOjos.setImageResource(R.mipmap.ic_notvisible); //Agregamos la imagen
        }
    }

    //Los sigueintes dos metodos son necesarios cuando tienes multiples botones con ojos
    public void OjosContraseñasOnResumeMultiples(EditText contraseñaTexto, ImageButton VisibilidadOjos, boolean visible){
        if(visible){
            contraseñaTexto.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            contraseñaTexto.setSelection(contraseñaTexto.getText().length()); //Necesario para que el cursor se ponga hasta el final del texto
            VisibilidadOjos.setImageResource(R.mipmap.ic_visible); //Agregamos la imagen
        }else{
            contraseñaTexto.setTransformationMethod(PasswordTransformationMethod.getInstance());
            contraseñaTexto.setSelection(contraseñaTexto.getText().length()); //Necesario para que el cursor se ponga hasta el final del texto
            VisibilidadOjos.setImageResource(R.mipmap.ic_notvisible); //Agregamos la imagen
        }
    }
    public boolean OjosContraseñasMultiples(EditText contraseñaTexto, ImageButton VisibilidadOjos, boolean visible){
        if(visible){ //Los caracteres se veran
            contraseñaTexto.setTransformationMethod(PasswordTransformationMethod.getInstance());
            contraseñaTexto.setSelection(contraseñaTexto.getText().length()); //Necesario para que el cursor se ponga hasta el final del texto
            VisibilidadOjos.setImageResource(R.mipmap.ic_notvisible); //Agregamos la imagen
            visible = false;
            return visible;
        }else{ //Los caracteres permaneceran ocultos
            contraseñaTexto.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            contraseñaTexto.setSelection(contraseñaTexto.getText().length()); //Necesario para que el cursor se ponga hasta el final del texto
            VisibilidadOjos.setImageResource(R.mipmap.ic_visible); //Agregamos la imagen
            visible = true; //La proxima vez que se le oprima hará el efecto contrario
            return visible;
        }
    }
    //Metodo para conseguir la fecha actual
    public static String fechaHora(long time) {
        DateFormat format = new SimpleDateFormat("EEE, dd 'de' MMM 'del' yyyy 'a las' hh:mm aaa");
        return format.format(new Date(time));
    }

    public void MostrarDatePicker(Context context, final TextView textView){
        //Conseguir fecha actual completa
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        int mes = calendario.get(Calendar.MONTH);
        int año = calendario.get(Calendar.YEAR);
        //DateFormat.getInstance(DateFormat.DAY_OF_WEEK_IN_MONTH_FIELD).format(calendario.getTime());
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Formato completo de la fecha

                //String fecha = DateFormat.getDateInstance(DateFormat.FULL).format(calendario.getTime());
                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                textView.setText(fecha); //Escribe la fecha
                Activity_Entrada Act = new Activity_Entrada();
                Act.Fecha = fecha;
                // fechaInicioText.setText("Fecha de inicio:\n"+fecha); //Escribe la fecha
            }
        }, año, mes, dia);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + 1000); //Evitar que el usuario escoja una fecha nueva al dia actual
        //Para que no puedas escoger dias atras es setMin, y -1000
        datePickerDialog.show();
    }




}


//Clase para detectar si hay internet
class DetectaConexion{
    Context context;

    public DetectaConexion(Context context){
        this.context = context;
    }

    public boolean isConnected(){
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if(connectivity != null){
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if(info != null){
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }


    //Variables para revisar la conexion a internet
    Handler h = new Handler();
    Runnable runnable;
    public void ConexionPorSegundos(final ImageButton nowifibutton){
        h.postDelayed( runnable = new Runnable() {
            public void run() {
                if(isConnected()) {
                    nowifibutton.setEnabled(false);
                    nowifibutton.setVisibility(View.GONE);
                }else{
                    nowifibutton.setEnabled(true);
                    nowifibutton.setVisibility(View.VISIBLE);
                }

                h.postDelayed(runnable, 500);
            }
        }, 500);
    }

    public void DetenerContador(){
        h.removeCallbacks(runnable);
    }

    //Solo se ejecuta una vez
    public void startConexion(ImageButton nowifibutton){
        if(isConnected()){
            nowifibutton.setEnabled(false);
            nowifibutton.setVisibility(View.GONE);
        }else{
            nowifibutton.setEnabled(true);
            nowifibutton.setVisibility(View.VISIBLE);
        }
    }

    //Mensaje al oprimir el boton de no internet
    public void mensajeNoInternet(Context Activity){
        //Dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity);
        builder.setTitle("No hay conexión a internet");
        builder.setMessage("Es posible que " + "algunas opciones no funcionen correctamente. Por seguridad de datos mantenga la aplicación abierta u oculta hasta " +
                "que vuelva a entrar la conexión.");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.show();

        //Align del dialogo
        TextView messageView = dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.FILL);
    }
}

//clase que se encarga de manejar las variables estaticas
class VariablesEstaticas extends  android.app.Application{
    //Persistencia de datos
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CURRENT_USER_UID = "currentUserUID";
    public static final String IS_LOGGED = "isLogged";

    public static String CurrentUserUID; //Valor del current user UID //VARIABLE QUE DEBE GUARDARSE HASTA CUANDO SE CIERRA LA APP
    public static boolean isLoged = false; //VARIABLE QUE DEBE GUARDARSE HASTA CUANDO SE CIERRA LA APP
    //Listas para guardar los valores temporales para luego ser almacenadas a la base de datos y al auth cuando vuelva internet
    //static para que su valor se guarde, y final para que la lista no cambie, lo que contiene si puede cambiar
    public String UID = UUID.randomUUID().toString().replace("-", ""); //Generador
    //Variable estatica que tomará un control del uid que se vaya a guardar en la base de datos local
    public static boolean Locked = false;

    //Metodo que nos ayudara a guardar las variables desde la memoria del telefono
    public void GuardarDatos(SharedPreferences sharedPreferences, boolean estado, String uid){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(VariablesEstaticas.IS_LOGGED, estado);
        editor.putString(VariablesEstaticas.CURRENT_USER_UID, uid);
        editor.apply();
    }

    //Metodo que nos ayudara a cargar las variables desde la memoria del telefono
    public void CargarDatos(SharedPreferences sharedPreferences){
        VariablesEstaticas.CurrentUserUID = sharedPreferences.getString(VariablesEstaticas.CURRENT_USER_UID, "");
        VariablesEstaticas.isLoged = sharedPreferences.getBoolean(VariablesEstaticas.IS_LOGGED, false);
        Log.e("TEST","CurrentUser "+VariablesEstaticas.CurrentUserUID + " isLoged "+VariablesEstaticas.isLoged);
    }


}

class BaseDeDatos{
    VariablesEstaticas VE = new VariablesEstaticas();
    //Metodo encargado de mostrar algun elemento de la base de datos relacionado al usuario actual
    public void MostrarElementoUsuarioActual(DatabaseReference Database, String ElementoAConseguir, final TextView textView, final SharedPreferences sharedPreferences){
        Log.e("TEST", "CurrentUserUID en Mostrar: "+VariablesEstaticas.CurrentUserUID);
        Database.child(VariablesEstaticas.CurrentUserUID).child(ElementoAConseguir).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //if necesario por si ya no obtiene el valor del uid por alguna razon, de esta manera no crasheara
                //Se necesitan volver a cargar los datos porque cada cambio en la base de datos activa este ondatachange
                VE.CargarDatos(sharedPreferences);
                if(VariablesEstaticas.CurrentUserUID != null && !VariablesEstaticas.CurrentUserUID.equals("")) {
                    String elemento = dataSnapshot.getValue().toString();
                    textView.setText(elemento);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public int ValidarContraseña(String contraseñaStr, int detectorDeErroresPassword, EditText contraseña, String mensaje){
        if(contraseñaStr.isEmpty()){
            contraseña.setError(mensaje);
            contraseña.requestFocus();
            detectorDeErroresPassword++;
        }else if(contraseñaStr.length() < 6){
            contraseña.setError("La contraseña es muy corta");
            contraseña.requestFocus();
            detectorDeErroresPassword++;
        }else{
            detectorDeErroresPassword = 0;
        }
        return detectorDeErroresPassword;
    }

    public int ValidarCorreo(String correoStr, int detectorDeErroresCorreo, EditText correo, String mensaje){
        if(correoStr.isEmpty()){
            correo.setError(mensaje/*"Correo requerido"*/);
            correo.requestFocus();
            detectorDeErroresCorreo++;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(correoStr).matches()){
            correo.setError("Ingrese un email valido");
            correo.requestFocus();
            detectorDeErroresCorreo++;
        }else{
            detectorDeErroresCorreo = 0;
        }
        return detectorDeErroresCorreo;
    }

    public int ValidarUsuario(String usuarioStr, int detectorDeErroresUsuario, EditText usuario, String mensaje){
        if(usuarioStr.isEmpty()){
            usuario.setError(mensaje);
            usuario.requestFocus();
            detectorDeErroresUsuario++;
        }else{
            detectorDeErroresUsuario = 0;
        }
        return detectorDeErroresUsuario;
    }
    public void AlAñadirEntrada(String NombreDelProducto, String GradoMadurez, String CantidadProducto, String FechaEntrada){
        //clearData(); //limpiar textviews
        String ID = GenerarTimeStamp(); //ID unico basado en el tiempo en el que se consiguió
        //String FechaEntrada = MetodosUtiles.fechaHora(new Date().getTime()); //Fecha actual
        //Referencia para la BD de forma en que podamos meter una tabla dentro de ella
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //Referencia a la tabla del child:
        DatabaseReference Entrada = database.child("Entradas");
        //Guardamos los campos
        GettersDeEntradas g = new GettersDeEntradas(ID, NombreDelProducto, GradoMadurez, FechaEntrada, CantidadProducto, GradoMadurez);
        Entrada.child(ID).setValue(g);
    }

    public void AlAñadirMerma(String NombreDelProducto, /*String GradoMadurez,*/ String CantidadProducto, String FechaEntrada){
        //clearData(); //limpiar textviews
        String ID = GenerarTimeStamp(); //ID unico basado en el tiempo en el que se consiguió
        String FechaMerma = MetodosUtiles.fechaHora(new Date().getTime()); //Fecha actual
        //Referencia para la BD de forma en que podamos meter una tabla dentro de ella
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //Referencia a la tabla del child:
        DatabaseReference Merma = database.child("Mermas");
        //Guardamos los campos
        GettersDeMerma g = new GettersDeMerma(ID, NombreDelProducto, FechaEntrada, FechaMerma, CantidadProducto);
        Merma.child(ID).setValue(g);
    }


    public int AlRetirarProducto(String Estampa, String CantidadDeLaEntrada, String CantidadEscrita){
        int CantidadDeLaEntradaInt = Integer.parseInt(CantidadDeLaEntrada);
        int CantidadEscritaInt = Integer.parseInt(CantidadEscrita);
        int Resta = CantidadDeLaEntradaInt - CantidadEscritaInt;
        //Referencia para la BD de forma en que podamos meter una tabla dentro de ella
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //Referencia a la tabla del child:
        DatabaseReference Entrada = database.child("Entradas");
        if(Resta <= 0){
            Entrada.child(Estampa).removeValue();
        }else{
            Entrada.child(Estampa).child("CantidadProducto").setValue(Resta);
        }
        return Resta;
    }

    public boolean Revisando = false;
    //Metodo encargado de cambiar el color de los grados de madurez automáticamente
    public void RevisarFechasMerma(final Context contexto){
        Revisando=true; //Variable que nos ayudara a que este metodo no se vuelva a ejecutar si hay un problema de internet o si va muy lento
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //Referencia a la tabla del child:
        final DatabaseReference entradas = database.child("Entradas");
        entradas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()) //Recorremos cada campo de la tabla Entradas
                {
                    if(snapshot.getValue() != null) {
                            entradas.child(snapshot.getKey()).child("FechaEntrada").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        //Conseguimos la fecha de la base de datos
                                        String Fecha = snapshot.child("FechaEntrada").getValue().toString();
                                        String fromDate = Fecha;
                                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                        Date dtt = null;
                                        try {
                                            dtt = df.parse(fromDate); //La fecha esta en string, aqui la pasamos a date
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        Date ds = new Date(dtt.getTime());
                                        Calendar calendar = Calendar.getInstance();
                                        calendar.setTime(ds); // Configuramos la fecha que se recibe
                                        //calendar.add(Calendar.DAY_OF_YEAR, 7);  // numero de días a añadir, o restar en caso de días<0
                                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                                        String formatted = format1.format(calendar.getTime()); //Fecha con los dias añadidos o restados
                                        String fechaActual = format1.format(new Date()); //Fecha actual dd/MM/yyyy
                                        //Restar fechas
                                        try {
                                            Date date1 = format1.parse(formatted);
                                            Date date2 = format1.parse(fechaActual);
                                            long diff = date2.getTime() - date1.getTime();
                                            String timestamp = snapshot.child("Timestamp").getValue().toString();
                                            Log.e("Test", "Timestamp: "+timestamp+" "+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
                                            +" Original: "+snapshot.child("GradoMadurezOriginal").getValue().toString() + " Actual: "+
                                                    snapshot.child("GradoMadurez").getValue().toString());
                                            switch (snapshot.child("GradoMadurezOriginal").getValue().toString()){
                                                /*TODO:Crear un "GradoMadurezOriginal" en la base de datos para poder guiar mejor
                                                * al cambiar valores o eliminar timestamp
                                                * TODO: Ver eso que dice aldo sobre registrar entradas, que no te deje despues de cierto numero que
                                                * cuente dentro de la base datos
                                                * TODO: Este metodo se debe llamar cada 10 segundos, ponerlo en todos los activities
                                                * TODO: Agregar a este metodo lo relacionado a la merma, incluyendo lo del CSV
                                                * TODO: El CSV se genera solo si la merma automatica o manual es llamada
                                                * TODO: Aldo pase salidas ya puesto bonito
                                                * */
                                                //Dependiendo de los dias  y del color que se tenga actualmente cambiara o se retirara de la BD
                                                case "Green":
                                                    if(snapshot.child("GradoMadurez").getValue().toString() != null) {
                                                        if (snapshot.child("GradoMadurez").getValue().toString().equals("Green")) {
                                                            if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 18) {
                                                                entradas.child(timestamp).child("GradoMadurez").setValue("Yellow");
                                                            }
                                                        } else if (snapshot.child("GradoMadurez").getValue().toString().equals("Yellow")) {
                                                            if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 24) {
                                                                entradas.child(timestamp).child("GradoMadurez").setValue("Red");
                                                            }
                                                        } else if (snapshot.child("GradoMadurez").getValue().toString().equals("Red")) {
                                                            if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 30) {
                                                                entradas.child(timestamp).removeValue();
                                                            }
                                                        }
                                                    }
                                                    break;
                                                //Dependiendo de los dias  y del color que se tenga actualmente cambiara o se retirara de la BD
                                                case "Yellow":
                                                    if(snapshot.child("GradoMadurez").getValue().toString() != null) {
                                                        if (snapshot.child("GradoMadurez").getValue().toString().equals("Yellow")) {
                                                            if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 6) {
                                                                entradas.child(timestamp).child("GradoMadurez").setValue("Red");
                                                            }
                                                        } else if (snapshot.child("GradoMadurez").getValue().toString().equals("Red")) {
                                                            if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 12) {
                                                                entradas.child(timestamp).removeValue();
                                                            }
                                                        }
                                                    }
                                                    break;
                                                //Dependiendo de los dias  y del color que se tenga actualmente cambiara o se retirara de la BD
                                                case "Red":
                                                    if(snapshot.child("GradoMadurez").getValue().toString() != null) {
                                                        //Si los dias de diferencia son mayores a 30 se cambia el grado
                                                        if (snapshot.child("GradoMadurez").getValue().toString().equals("Red")) {
                                                            if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 6) {
                                                                entradas.child(timestamp).removeValue();
                                                            }
                                                        }
                                                    }
                                                    break;
                                            }
                                            if(snapshot.child("GradoMadurez").getValue().toString() != null) {
                                                Log.e("Test", "DESPUES TERMINAR EL SWITCH: Timestamp: " + timestamp + " " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
                                                        + " Original: " + snapshot.child("GradoMadurezOriginal").getValue().toString() + " Actual: " +
                                                        snapshot.child("GradoMadurez").getValue().toString()+"\n");
                                            }
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                     @Override
                                     public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }
                                }
                        );
                    }
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Variables para revisar la merma y grados de madurez
    Handler h = new Handler();
    Runnable runnable;
    public void RevisarCada15SegundosEstadoGrados(final Context context){
        h.postDelayed( runnable = new Runnable() {
            public void run() {
                if(!Revisando) {
                    RevisarFechasMerma(context);
                }
                h.postDelayed(runnable, 15000);
            }
        }, 15000);
    }

    public void DetenerContadorMerma(){
        h.removeCallbacks(runnable);
    }

    //Metodo encargado de generar una ficha unica para basada en el tiempo
    public String GenerarTimeStamp(){
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String timeStr = ts+"";
        timeStr = timeStr.replace("."," ");
        return  timeStr;
    }
}