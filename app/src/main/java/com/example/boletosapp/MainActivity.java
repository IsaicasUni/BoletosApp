package com.example.boletosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumBoleto;
    private EditText txtNombreCliente;
    private EditText txtEdadCliente;
    private EditText txtFecha;
    private EditText txtPrecio;
    private Spinner spnDestinos;
    private Spinner spnTipoBoleto;
    private Button btnImprimir;
    private Button btnVaciar;
    private TextView lblResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumBoleto = (EditText) findViewById(R.id.txtNumBoleto);
        txtNombreCliente = (EditText) findViewById(R.id.txtNombreCliente);
        txtEdadCliente = (EditText) findViewById(R.id.txtEdadCliente);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        txtPrecio = (EditText) findViewById(R.id.txtPrecio);
        spnDestinos = (Spinner) findViewById(R.id.spnDestinos);
        spnTipoBoleto = (Spinner) findViewById(R.id.spnTipoBoleto);
        btnImprimir = (Button) findViewById(R.id.btnImprimir);
        btnVaciar = (Button) findViewById(R.id.btnVaciar);
        lblResultado = (TextView) findViewById(R.id.lblResultado);

        Boletos Boleto = new Boletos();

        ArrayAdapter<String> AdaptadorDestino = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.destinos));
        spnDestinos.setAdapter(AdaptadorDestino);

        ArrayAdapter<String> AdaptadorTipoBoleto = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.tipoBoleto));
        spnTipoBoleto.setAdapter(AdaptadorTipoBoleto);

        btnImprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNumBoleto.getText().toString().matches("") || txtNombreCliente.getText().toString().matches("") ||
                        spnDestinos.getSelectedItem().toString().trim().equals("") ||
                        spnTipoBoleto.getSelectedItem().toString().trim().equals("") ||
                        txtPrecio.getText().toString().matches("") ||
                        txtFecha.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Favor de rellenar los datos",Toast.LENGTH_LONG).show();
                } else{
                    int numBoleto = Integer.parseInt(txtNumBoleto.getText().toString());
                    String nomCliente = txtNombreCliente.getText().toString();
                    int edad = Integer.parseInt(txtEdadCliente.getText().toString());
                    String destino = spnDestinos.getSelectedItem().toString();
                    String tipoViaje = spnTipoBoleto.getSelectedItem().toString();
                    double precio = Double.parseDouble(txtPrecio.getText().toString());
                    String fecha = txtFecha.getText().toString();

                    Boleto.setNumBoleto(numBoleto);
                    Boleto.setNomCliente(nomCliente);
                    Boleto.setDestino(destino);
                    Boleto.setTipoViaje(tipoViaje);
                    Boleto.setPrecio(precio);
                    Boleto.setFecha(fecha);

                    lblResultado.setText("---INFORMACIÓN DE BOLETO---" +
                            "\n\nNúmero de boleto: " + Boleto.getNumBoleto() +
                            "\nNombre del cliente: " + Boleto.getNomCliente() +
                            "\nDestino: " + Boleto.getDestino() +
                            "\nTipo de viaje: " + Boleto.getTipoViaje() +
                            "\nPrecio: $" + Boleto.getPrecio() +
                            "\nFecha: " + Boleto.getFecha() +
                            "\n\n***IMPORTE***" +
                            "\n\nSubtotal: " + Boleto.calcularSubtotal() +
                            "\nImpuesto: " + Boleto.calcularImpuesto() +
                            "\nDescuento: " + Boleto.calcularDescuento(edad) +
                            "\nTotal a pagar: " + Boleto.calcularTotal(edad));
                }
            }
        });

        btnVaciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lblResultado.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Campos no rellenados",Toast.LENGTH_LONG).show();
                } else {
                    txtNumBoleto.setText("");
                    txtNombreCliente.setText("");
                    txtEdadCliente.setText("");
                    spnDestinos.setSelection(0);
                    spnTipoBoleto.setSelection(0);
                    txtPrecio.setText("");
                    txtFecha.setText("");
                    lblResultado.setText("");
                }
            }
        });

    }
}