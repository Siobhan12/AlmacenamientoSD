package com.siobcode.almacenamientosd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

    public class MainActivity extends Activity {
        EditText etnombre;
        EditText etcontenido;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            etnombre = (EditText) findViewById(R.id.edtnombre);
            etcontenido = (EditText) findViewById(R.id.edtcontenido);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_main, menu);
            return true;
        }

        public void grabar(View v) {
            String nomarchivo = etnombre.getText().toString();
            String contenido = etcontenido.getText().toString();
            try {
                File tarjeta = Environment.getExternalStorageDirectory();
                File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
                OutputStreamWriter osw = new OutputStreamWriter(
                        new FileOutputStream(file));
                osw.write(contenido);
                osw.flush();
                osw.close();
                Toast.makeText(this, "Los datos fueron grabados correctamente",
                        Toast.LENGTH_SHORT).show();
                etnombre.setText("");
                etcontenido.setText("");
            } catch (IOException ioe) {
            }
        }

        public void recuperar(View v) {
            String nomarchivo = etnombre.getText().toString();
            File tarjeta = Environment.getExternalStorageDirectory();
            File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
            try {
                FileInputStream fIn = new FileInputStream(file);
                InputStreamReader archivo = new InputStreamReader(fIn);
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                etcontenido.setText(todo);

            } catch (IOException e) {
            }
        }
    }
