package com.example.basedatostienda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {

    EditText etName,et,etEmail;

    Button button;

    RequestQueue requestQueue;

    String Name,product,email;

    ProgressDialog progressDialog;

    String sendUrl="https://pruebas00017.000webhostapp.com/test/connection.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.etName);
        et=findViewById(R.id.et);
        etEmail=findViewById(R.id.etEmail);
        Button button = findViewById(R.id.button);


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        progressDialog=new ProgressDialog(MainActivity.this);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setMessage("Espera mientras inserta la informacion");
                progressDialog.show();
                GetValueFromEditText();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, sendUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();

                            }
                        },
                        new Response.ErrorListener() {
                            private VolleyError error;
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("user", Name);
                        params.put("product", product);
                        params.put("email",email);
                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);

            }
        });

    }

    // Creating method to get value from EditText.
    public void GetValueFromEditText(){

        Name = etName.getText().toString().trim();
        product= et.getText().toString().trim();
        email = etEmail.getText().toString().trim();
    }

    }

