package andrezz.cajero;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MenuCajero extends ActionBarActivity {
    String cuenta, user;
    String []users=new String[3];
    String []cuentas=new String[3];
    double []montos=new double[3];
    double monto;
    TextView tvCuenta, tvMonto, tvUser;
    String []tr=new String[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cajero);
        tvCuenta= (TextView) findViewById(R.id.tvCuenta);
        tvUser= (TextView) findViewById(R.id.tvUsuario);
        tvMonto= (TextView) findViewById(R.id.tvSaldo);
        cuenta = getIntent().getStringExtra("cuenta");
        user = getIntent().getStringExtra("user");
        monto = getIntent().getDoubleExtra("monto", 0.0);
        users = getIntent().getStringArrayExtra("users");
        cuentas = getIntent().getStringArrayExtra("cuentas");
        montos = getIntent().getDoubleArrayExtra("montos");
        tr=getIntent().getStringArrayExtra("tra");
        escribirPantalla();
    }

    private void escribirPantalla() {
        tvCuenta.setText(" "+cuenta);
        tvMonto.setText(" "+monto);
        tvUser.setText(" "+user);
    }

    private void lanzarIntent(Intent intent){
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
    public void abrirRetiro(View view){
        Intent intent= new Intent(this, RetiroCajero.class);
        lanzarIntent(intent);
    }
    public void abrirTransferencia(View view){
        Intent intent= new Intent(this, TransferenciaCajero.class);
        lanzarIntent(intent);
    }
    public void abrirConsulta(View view){
        Intent intent= new Intent(this, ConsultaCajero.class);
        lanzarIntent(intent);
    }
    public void abrirLogin(View view){
        Intent intent= new Intent(this, MainActivity.class);
        lanzarIntent(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_cajero, menu);
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
