package andrezz.cajero;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TransferenciaCajero extends ActionBarActivity {
    String cuenta, user;
    String[] users = new String[3];
    String[] cuentas = new String[3];
    double[] montos = new double[3];
    String[] tr;
    EditText txtMonto, txtCuenta;
    double monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia_cajero);
        txtCuenta= (EditText) findViewById(R.id.txtCtaDestino);
        txtMonto= (EditText) findViewById(R.id.txtMonto);
        cuenta = getIntent().getStringExtra("cuenta");
        user = getIntent().getStringExtra("user");
        monto = getIntent().getDoubleExtra("monto", 0.0);
        users = getIntent().getStringArrayExtra("users");
        cuentas = getIntent().getStringArrayExtra("cuentas");
        montos = getIntent().getDoubleArrayExtra("montos");
        tr = getIntent().getStringArrayExtra("tra");
    }
    public void transferir(View view){
        String c= txtCuenta.getText().toString();
        double m= Double.parseDouble(txtMonto.getText().toString());
        boolean t=false;
        for(int i=0; i<users.length; i++){
            if(c.equals(cuentas[i])){
                if(monto>=m){
                    t=true;
                    montos[i]=montos[i]+m;
                    registrarTransaccion(cuentas[i]+";Deposito a Cuenta;" + m + ";");
                }else{
                    Toast.makeText(this,"Saldo Insuficiente",Toast.LENGTH_SHORT).show();
                }

            }
        }
        if(t){
            Toast.makeText(this,"Transferencia Exitosa",Toast.LENGTH_SHORT).show();
            registrarTransaccion(cuenta+";Transferencia de Dinero;" + m + ";");
        }else{
            Toast.makeText(this,"Numero de Cuenta Invalido",Toast.LENGTH_SHORT).show();
        }
    }

    private void registrarTransaccion(String m) {
        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("DD/MM/yyyy HH:mm:ss");
        if (tr != null) {
            for (int i = 0; i < tr.length; i++) {
                if (tr[i] == null) {
                    tr[i] = m + fecha.format(date);
                    break;
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transferencia_cajero, menu);
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
