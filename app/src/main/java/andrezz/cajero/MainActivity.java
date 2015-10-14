package andrezz.cajero;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText txtClave, txtNCuenta;
    String[] users = new String[3];
    String[] claves = new String[3];
    String[] cuentas = new String[3];
    double[] montos = new double[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtClave = (EditText) findViewById(R.id.txtClave);
        txtNCuenta = (EditText) findViewById(R.id.txtNCta);
        users = getIntent().getStringArrayExtra("users");
        cuentas = getIntent().getStringArrayExtra("cuentas");
        montos = getIntent().getDoubleArrayExtra("montos");
        if (users == null) {
            users = new String[3];
            users[0] = "User1";
            users[1] = "User2";
            users[2] = "User3";
        }
        claves = new String[3];
        claves[0] = "1234";
        claves[1] = "4321";
        claves[2] = "1212";
        if (cuentas == null) {
            cuentas = new String[3];
            cuentas[0] = "123456";
            cuentas[1] = "654321";
            cuentas[2] = "121212";
        }
        if (montos == null) {
            montos = new double[3];
            montos[0] = 100.0;
            montos[1] = 657.0;
            montos[2] = 3456.6;
        }

    }

    public void abrirMenu(String user, String cuenta, double monto) {
        Intent intent = new Intent(this, MenuCajero.class);
        intent.putExtra("user", user);
        intent.putExtra("cuenta", cuenta);
        intent.putExtra("monto", monto);
        intent.putExtra("users",users);
        intent.putExtra("cuentas",cuentas);
        intent.putExtra("montos",montos);
        startActivity(intent);
    }

    public void validar(View view) {
        String clave = txtClave.getText().toString();
        String cta = txtNCuenta.getText().toString();
        boolean login = false;
        for (int i = 0; i < users.length; i++) {
            if (clave.equals(claves[i]) && cta.equals(cuentas[i])) {
                login = true;
                abrirMenu(users[i], cuentas[i], montos[i]);
            }
        }
        if (login == false) {
            Toast.makeText(this, "Cuenta o clave Incorrectas", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
