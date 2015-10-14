package andrezz.cajero;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RetiroCajero extends ActionBarActivity {
    private String m_retiro = "";
    String cuenta, user;
    String[] users = new String[3];
    String[] cuentas = new String[3];
    double[] montos = new double[3];
    String []tr;
    double monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retiro_cajero);

        cuenta = getIntent().getStringExtra("cuenta");
        user = getIntent().getStringExtra("user");
        monto = getIntent().getDoubleExtra("monto", 0.0);
        users = getIntent().getStringArrayExtra("users");
        cuentas = getIntent().getStringArrayExtra("cuentas");
        montos = getIntent().getDoubleArrayExtra("montos");
        tr = getIntent().getStringArrayExtra("tra");
    }

    public void retiro100(View view) {
        retirar(100);
    }

    public void retiro200(View view) {
        retirar(200);
    }

    public void retiro500(View view) {
        retirar(500);
    }

    public void retiro800(View view) {
        retirar(800);
    }

    public void retiro1500(View view) {
        retirar(1500);
    }

    private void retirar(double cantidad) {
        if(cantidad<=monto){
            monto=monto-cantidad;
            for (int i = 0; i < users.length; i++) {
                if ( cuenta.equals(cuentas[i])) {
                    montos[i]=montos[i]-cantidad;
                }
            }
            msg("Operacion Exitosa");
            registrarTransaccion(monto);
            regresarMenu();
        }else{
            msg("No hay suficiente Saldo");
        }
    }
        private void registrarTransaccion(double monto){
        Date date= new Date();
        SimpleDateFormat fecha= new SimpleDateFormat("DD/MM/yyyy HH:mm:ss");
        if(tr!=null){
            for(int i=0;i<tr.length;i++){
                if(tr[i]==null){
                    tr[i]=cuenta+";Retiro de Dinero;"+monto+";"+fecha.format(date);
                    break;
                }
            }
        }
    }
    private void msg(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
    private void regresarMenu(){
        Intent intent= new Intent(this, MenuCajero.class);
        intent.putExtra("user",user);
        intent.putExtra("monto",monto);
        intent.putExtra("cuenta",cuenta);
        intent.putExtra("users",users);
        intent.putExtra("cuentas",cuentas);
        intent.putExtra("montos",montos);
        intent.putExtra("tra",tr);
        startActivity(intent);
        finish();
    }

    public void retiroOtro(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Escribir Monto");
        // Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_retiro = input.getText().toString();
                try {
                    double x= Double.parseDouble(m_retiro);
                    retirar(x);
                }catch (Exception e){
                    msg("Error de Datos");
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retiro_cajero, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
