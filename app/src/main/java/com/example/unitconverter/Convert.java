package com.example.unitconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Convert extends AppCompatActivity {
    TextView titletoolbar;
    public static double inr,eur,jpy,gbp,chf,aud,cad,usd;
    RequestQueue requestQueue;
    String to,from;
    EditText editfrom,editto;
    ArrayAdapter adapter;
    Spinner spinnerfrom,spinnerto;
    DecimalFormat d=new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        requestQueue = Volley.newRequestQueue(Convert.this);

        spinnerto=findViewById(R.id.spinnerinput);
         spinnerfrom=findViewById(R.id.spinneroutput);
         titletoolbar=findViewById(R.id.texttoolbar);
         editto=findViewById(R.id.outputvalue);
         editfrom=findViewById(R.id.inputvalue);

        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        titletoolbar.setText(title);

        if(title.equalsIgnoreCase("temperature")){
            String[] temp={"Celsius (C)","Fahrenheit (F)","Kelvin (K)","Rankine (R)"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,temp);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        else if(title.equalsIgnoreCase("currency")){
            String[] currency={"USD","INR","EUR","JPY","GBP","CHF","AUD","CAD"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,currency);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        else if(title.equalsIgnoreCase("pressure")){
            String[] pressure={"Pascal (Pa)","Atmosphere (atm)","Bar","Torr"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,pressure);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        else if(title.equalsIgnoreCase("speed")){
            String[] speed={"m/s","ft/s","km/h","mi/h(mph)","mach"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,speed);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);

        }
        else if(title.equalsIgnoreCase("time")){
            String[] time={"ms","sec","min","hour","day","week","year"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,time);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);

        }
        else if(title.equalsIgnoreCase("frequency")){
            String[] freq={"Hz","MHz","KHz","Rpm"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,freq);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        else if(title.equalsIgnoreCase("number system")){
            String[] number={"Binary","Octal","Decimal","Hexadecimal"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,number);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        else if(title.equalsIgnoreCase("weight")){
            String[] weight={"ug","mg","g","kg","lbs(pound)","oz","tonne"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,weight);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        else if(title.equalsIgnoreCase("area")){
            String[] area={"square mm","square cm","square mtr","square ft","acre","square km"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,area);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        else if(title.equalsIgnoreCase("volume")){
            String[] volume={"ml","L(litre)","cubic mm","cubic cm","cubic mtr","gal"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,volume);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        else if(title.equalsIgnoreCase("storage")){
            String[] storage={"bit","Byte","kB","mB","gB","tB"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,storage);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        else if(title.equalsIgnoreCase("distance")){
            String[] distance={"um","mm","cm","m","inch","ft","yd","fathom","mile","km"};
            adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,distance);
            spinnerto.setAdapter(adapter);
            spinnerfrom.setAdapter(adapter);
        }
        spinnerto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerfrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editfrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                convert();
            }
        });
    }
    public void currencyusd(String fromvalue){
        StringRequest request=new StringRequest(Request.Method.GET,"https://api.exchangerate-api.com/v4/latest/USD", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject quotes=object.getJSONObject("rates");
                    inr=quotes.getDouble("INR");
                    eur=quotes.getDouble("EUR");
                    jpy=quotes.getDouble("JPY");
                    gbp=quotes.getDouble("GBP");
                    chf=quotes.getDouble("CHF");
                    aud=quotes.getDouble("AUD");
                    cad=quotes.getDouble("CAD");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Convert.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        if (to.equalsIgnoreCase("USD")) {
            editto.setText(fromvalue);
        } else if (to.equalsIgnoreCase("INR")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*inr))+"");
        } else if (to.equalsIgnoreCase("EUR")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*eur))+"");
        } else if (to.equalsIgnoreCase("JPY")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*jpy))+"");
        } else if (to.equalsIgnoreCase("GBP")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*gbp))+"");
        } else if (to.equalsIgnoreCase("CHF")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*chf))+"");
        } else if (to.equalsIgnoreCase("AUD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*aud))+"");
        } else if (to.equalsIgnoreCase("CAD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*cad))+"");
        }
    }
    public void currencyinr(String fromvalue){
        StringRequest request=new StringRequest(Request.Method.GET,"https://api.exchangerate-api.com/v4/latest/INR", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject quotes=object.getJSONObject("rates");
                    usd=quotes.getDouble("USD");
                    eur=quotes.getDouble("EUR");
                    jpy=quotes.getDouble("JPY");
                    gbp=quotes.getDouble("GBP");
                    chf=quotes.getDouble("CHF");
                    aud=quotes.getDouble("AUD");
                    cad=quotes.getDouble("CAD");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Convert.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        if (to.equalsIgnoreCase("INR")) {
            editto.setText(fromvalue);
        } else if (to.equalsIgnoreCase("USD")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*usd))+"");
        } else if (to.equalsIgnoreCase("EUR")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*eur))+"");
        } else if (to.equalsIgnoreCase("JPY")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*jpy))+"");
        } else if (to.equalsIgnoreCase("GBP")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*gbp))+"");
        } else if (to.equalsIgnoreCase("CHF")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*chf))+"");
        } else if (to.equalsIgnoreCase("AUD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*aud))+"");
        } else if (to.equalsIgnoreCase("CAD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*cad))+"");
        }

    }
    public void currencygbp(String fromvalue){
        StringRequest request=new StringRequest(Request.Method.GET,"https://api.exchangerate-api.com/v4/latest/GBP", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject quotes=object.getJSONObject("rates");
                    inr=quotes.getDouble("INR");
                    eur=quotes.getDouble("EUR");
                    jpy=quotes.getDouble("JPY");
                    usd=quotes.getDouble("USD");
                    chf=quotes.getDouble("CHF");
                    aud=quotes.getDouble("AUD");
                    cad=quotes.getDouble("CAD");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Convert.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        if (to.equalsIgnoreCase("GBP")) {
            editto.setText(fromvalue);
        } else if (to.equalsIgnoreCase("INR")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*inr))+"");
        } else if (to.equalsIgnoreCase("EUR")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*eur))+"");
        } else if (to.equalsIgnoreCase("JPY")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*jpy))+"");
        } else if (to.equalsIgnoreCase("USD")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*usd))+"");
        } else if (to.equalsIgnoreCase("CHF")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*chf))+"");
        } else if (to.equalsIgnoreCase("AUD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*aud))+"");
        } else if (to.equalsIgnoreCase("CAD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*cad))+"");
        }

    }
    public void currencyaud(String fromvalue){
        StringRequest request=new StringRequest(Request.Method.GET,"https://api.exchangerate-api.com/v4/latest/AUD", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject quotes=object.getJSONObject("rates");
                    inr=quotes.getDouble("INR");
                    eur=quotes.getDouble("EUR");
                    jpy=quotes.getDouble("JPY");
                    gbp=quotes.getDouble("GBP");
                    chf=quotes.getDouble("CHF");
                    usd=quotes.getDouble("USD");
                    cad=quotes.getDouble("CAD");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Convert.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        if (to.equalsIgnoreCase("AUD")) {
            editto.setText(fromvalue);
        } else if (to.equalsIgnoreCase("INR")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*inr))+"");
        } else if (to.equalsIgnoreCase("EUR")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*eur))+"");
        } else if (to.equalsIgnoreCase("JPY")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*jpy))+"");
        } else if (to.equalsIgnoreCase("GBP")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*gbp))+"");
        } else if (to.equalsIgnoreCase("CHF")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*chf))+"");
        } else if (to.equalsIgnoreCase("USD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*usd))+"");
        } else if (to.equalsIgnoreCase("CAD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*cad))+"");
        }

    }
    public void currencycad(String fromvalue){
        StringRequest request=new StringRequest(Request.Method.GET,"https://api.exchangerate-api.com/v4/latest/CAD", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject quotes=object.getJSONObject("rates");
                    inr=quotes.getDouble("INR");
                    eur=quotes.getDouble("EUR");
                    jpy=quotes.getDouble("JPY");
                    gbp=quotes.getDouble("GBP");
                    chf=quotes.getDouble("CHF");
                    aud=quotes.getDouble("AUD");
                    usd=quotes.getDouble("USD");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Convert.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        if (to.equalsIgnoreCase("CAD")) {
            editto.setText(fromvalue);
        } else if (to.equalsIgnoreCase("INR")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*inr))+"");
        } else if (to.equalsIgnoreCase("EUR")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*eur))+"");
        } else if (to.equalsIgnoreCase("JPY")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*jpy))+"");
        } else if (to.equalsIgnoreCase("GBP")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*gbp))+"");
        } else if (to.equalsIgnoreCase("CHF")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*chf))+"");
        } else if (to.equalsIgnoreCase("AUD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*aud))+"");
        } else if (to.equalsIgnoreCase("USD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*usd))+"");
        }

    }
    public void currencychf(String fromvalue){
        StringRequest request=new StringRequest(Request.Method.GET,"https://api.exchangerate-api.com/v4/latest/CHF", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject quotes=object.getJSONObject("rates");
                    inr=quotes.getDouble("INR");
                    eur=quotes.getDouble("EUR");
                    jpy=quotes.getDouble("JPY");
                    gbp=quotes.getDouble("GBP");
                    usd=quotes.getDouble("USD");
                    aud=quotes.getDouble("AUD");
                    cad=quotes.getDouble("CAD");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Convert.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        if (to.equalsIgnoreCase("CHF")) {
            editto.setText(fromvalue);
        } else if (to.equalsIgnoreCase("INR")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*inr))+"");
        } else if (to.equalsIgnoreCase("EUR")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*eur))+"");
        } else if (to.equalsIgnoreCase("JPY")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*jpy))+"");
        } else if (to.equalsIgnoreCase("GBP")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*gbp))+"");
        } else if (to.equalsIgnoreCase("USD")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*usd))+"");
        } else if (to.equalsIgnoreCase("AUD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*aud))+"");
        } else if (to.equalsIgnoreCase("CAD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*cad))+"");
        }

    }
    public void currencyjpy(String fromvalue){
        StringRequest request=new StringRequest(Request.Method.GET,"https://api.exchangerate-api.com/v4/latest/JPY", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject quotes=object.getJSONObject("rates");
                    inr=quotes.getDouble("INR");
                    eur=quotes.getDouble("EUR");
                    usd=quotes.getDouble("USD");
                    gbp=quotes.getDouble("GBP");
                    chf=quotes.getDouble("CHF");
                    aud=quotes.getDouble("AUD");
                    cad=quotes.getDouble("CAD");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Convert.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        if (to.equalsIgnoreCase("JPY")) {
            editto.setText(fromvalue);
        } else if (to.equalsIgnoreCase("INR")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*inr))+"");
        } else if (to.equalsIgnoreCase("EUR")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*eur))+"");
        } else if (to.equalsIgnoreCase("USD")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*usd))+"");
        } else if (to.equalsIgnoreCase("GBP")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*gbp))+"");
        } else if (to.equalsIgnoreCase("CHF")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*chf))+"");
        } else if (to.equalsIgnoreCase("AUD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*aud))+"");
        } else if (to.equalsIgnoreCase("CAD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*cad))+"");
        }

    }
    public void currencyeur(String fromvalue){
        StringRequest request=new StringRequest(Request.Method.GET,"https://api.exchangerate-api.com/v4/latest/EUR", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject quotes=object.getJSONObject("rates");
                    inr=quotes.getDouble("INR");
                    usd=quotes.getDouble("USD");
                    jpy=quotes.getDouble("JPY");
                    gbp=quotes.getDouble("GBP");
                    chf=quotes.getDouble("CHF");
                    aud=quotes.getDouble("AUD");
                    cad=quotes.getDouble("CAD");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Convert.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
        if (to.equalsIgnoreCase("EUR")) {
            editto.setText(fromvalue);
        } else if (to.equalsIgnoreCase("INR")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*inr))+"");
        } else if (to.equalsIgnoreCase("USD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*usd))+"");
        } else if (to.equalsIgnoreCase("JPY")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*jpy))+"");
        } else if (to.equalsIgnoreCase("GBP")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*gbp))+"");
        } else if (to.equalsIgnoreCase("CHF")) {
            editto.setText((d.format(Float.parseFloat(fromvalue)*chf))+"");
        } else if (to.equalsIgnoreCase("AUD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*aud))+"");
        } else if (to.equalsIgnoreCase("CAD")){
            editto.setText((d.format(Float.parseFloat(fromvalue)*cad))+"");
        }

    }
    public void convert(){
        from=spinnerfrom.getSelectedItem().toString();
        to=spinnerto.getSelectedItem().toString();
        String fromvalue=editfrom.getText().toString();
        if(!fromvalue.isEmpty()) {
            if (from.equalsIgnoreCase("Celsius (C)")) {
                if (to.equalsIgnoreCase("Celsius (C)")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("Fahrenheit (F)")) {
                    editto.setText(((Float.parseFloat(fromvalue) * (9.0f / 5.0f)) + 32) + "");
                } else if (to.equalsIgnoreCase("Kelvin (K)")) {
                    editto.setText((Float.parseFloat(fromvalue) + 273.15f) + "");
                } else if (to.equalsIgnoreCase("Rankine (R)")) {
                    editto.setText(((Float.parseFloat(fromvalue) * (9.0f / 5.0f)) + 491.67) + "");
                }
            } else if (from.equalsIgnoreCase("Fahrenheit (F)")) {
                if (to.equalsIgnoreCase("Celsius (C)")) {
                    editto.setText(((Float.parseFloat(fromvalue) - 32) * (5.0f / 9.0f)) + "");
                } else if (to.equalsIgnoreCase("Fahrenheit (F)")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("Kelvin (K)")) {
                    editto.setText((((Float.parseFloat(fromvalue) - 32) * (5.0f / 9.0f)) + 273.15) + "");
                } else if (to.equalsIgnoreCase("Rankine (R)")) {
                    editto.setText((Float.parseFloat(fromvalue) + 459.67) + "");
                }
            } else if (from.equalsIgnoreCase("Kelvin (K)")) {
                if (to.equalsIgnoreCase("Celsius (C)")) {
                    editto.setText((Float.parseFloat(fromvalue) - 273.15f) + "");
                } else if (to.equalsIgnoreCase("Fahrenheit (F)")) {
                    editto.setText((((Float.parseFloat(fromvalue) - 273.15) * (9.0f / 5.0f)) + 32) + "");
                } else if (to.equalsIgnoreCase("Kelvin (K)")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("Rankine (R)")) {
                    editto.setText((Float.parseFloat(fromvalue) * 1.8) + "");
                }
            } else if (from.equalsIgnoreCase("Rankine (R)")) {
                if (to.equalsIgnoreCase("Celsius (C)")) {
                    editto.setText(((Float.parseFloat(fromvalue) - 491.67) * (5.0f / 9.0f)) + "");
                } else if (to.equalsIgnoreCase("Fahrenheit (F)")) {
                    editto.setText((Float.parseFloat(fromvalue) - 459.67) + "");
                } else if (to.equalsIgnoreCase("Kelvin (K)")) {
                    editto.setText((Float.parseFloat(fromvalue) / 1.8) + "");
                } else if (to.equalsIgnoreCase("Rankine (R)")) {
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("Pascal (Pa)")) {
                if (to.equalsIgnoreCase("Torr")) {
                    editto.setText((Float.parseFloat(fromvalue) / 133.322) + "");
                } else if (to.equalsIgnoreCase("Bar")) {
                    editto.setText((Float.parseFloat(fromvalue) / 100000) + "");
                } else if (to.equalsIgnoreCase("Atmosphere (atm)")) {
                    editto.setText((Float.parseFloat(fromvalue) / 101325) + "");
                } else if (to.equalsIgnoreCase("Pascal (Pa)")) {
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("Atmosphere (atm)")) {
                if (to.equalsIgnoreCase("Torr")) {
                    editto.setText((Float.parseFloat(fromvalue) * 760) + "");
                } else if (to.equalsIgnoreCase("Bar")) {
                    editto.setText((Float.parseFloat(fromvalue) * 1.013) + "");
                } else if (to.equalsIgnoreCase("Atmosphere (atm)")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("Pascal (Pa)")) {
                    editto.setText((Float.parseFloat(fromvalue) * 101325) + "");
                }
            } else if (from.equalsIgnoreCase("Bar")) {
                if (to.equalsIgnoreCase("Torr")) {
                    editto.setText((Float.parseFloat(fromvalue) * 750.062) + "");
                } else if (to.equalsIgnoreCase("Bar")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("Atmosphere (atm)")) {
                    editto.setText((Float.parseFloat(fromvalue) / 1.013) + "");
                } else if (to.equalsIgnoreCase("Pascal (Pa)")) {
                    editto.setText((Float.parseFloat(fromvalue) * 100000) + "");
                }
            } else if (from.equalsIgnoreCase("Torr")) {
                if (to.equalsIgnoreCase("Torr")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("Bar")) {
                    editto.setText((Float.parseFloat(fromvalue) / 750.062) + "");
                } else if (to.equalsIgnoreCase("Atmosphere (atm)")) {
                    editto.setText((Float.parseFloat(fromvalue) * 760) + "");
                } else if (to.equalsIgnoreCase("Pascal (Pa)")) {
                    editto.setText((Float.parseFloat(fromvalue) * 133.322) + "");
                }
            } else if (from.equalsIgnoreCase("bit")) {
                if (to.equalsIgnoreCase("bit")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("byte")) {
                    editto.setText((Float.parseFloat(fromvalue)/8)+"");
                } else if (to.equalsIgnoreCase("kb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(8*1024)+""));
                } else if (to.equalsIgnoreCase("mb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(8*1024*1024)+""));
                } else if (to.equalsIgnoreCase("gb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(8*1024*1024*1024)+""));
                } else if (to.equalsIgnoreCase("tb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(8*1024*1024*1024*1024)+""));
                }
            } else if (from.equalsIgnoreCase("byte")) {
                if (to.equalsIgnoreCase("bit")) {
                    editto.setText((Float.parseFloat(fromvalue)*8)+"");
                } else if (to.equalsIgnoreCase("byte")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("kb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024)+""));
                } else if (to.equalsIgnoreCase("mb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024*1024)+""));
                } else if (to.equalsIgnoreCase("gb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024*1024*1024)+""));
                } else if (to.equalsIgnoreCase("tb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024*1024*1024*1024)+""));
                }
            } else if (from.equalsIgnoreCase("kb")) {
                if (to.equalsIgnoreCase("bit")) {
                    editto.setText((Float.parseFloat(fromvalue)*(8*1024)+""));
                } else if (to.equalsIgnoreCase("byte")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024)+""));
                } else if (to.equalsIgnoreCase("kb")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("mb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024)+""));
                } else if (to.equalsIgnoreCase("gb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024*1024)+""));
                } else if (to.equalsIgnoreCase("tb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024*1024*1024)+""));
                }
            } else if (from.equalsIgnoreCase("mb")) {
                if (to.equalsIgnoreCase("bit")) {
                    editto.setText((Float.parseFloat(fromvalue)*(8*1024*1024)+""));
                } else if (to.equalsIgnoreCase("byte")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024*1024)+""));
                } else if (to.equalsIgnoreCase("kb")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024)+""));
                } else if (to.equalsIgnoreCase("mb")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("gb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024)+""));
                } else if (to.equalsIgnoreCase("tb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024*1024)+""));
                }
            } else if (from.equalsIgnoreCase("gb")) {
                if (to.equalsIgnoreCase("bit")) {
                    editto.setText((Float.parseFloat(fromvalue)*(8*1024*1024*1024)+""));
                } else if (to.equalsIgnoreCase("byte")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024*1024*1024)+""));
                } else if (to.equalsIgnoreCase("kb")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024*1024)+""));
                } else if (to.equalsIgnoreCase("mb")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024)+""));
                } else if (to.equalsIgnoreCase("gb")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("tb")) {
                    editto.setText((Float.parseFloat(fromvalue)/(1024)+""));
                }
            } else if (from.equalsIgnoreCase("tb")) {
                if (to.equalsIgnoreCase("bit")){
                    editto.setText((Float.parseFloat(fromvalue)*(8*1024*1024*1024*1024)+""));
                } else if (to.equalsIgnoreCase("byte")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024*1024*1024*1024)+""));
                } else if (to.equalsIgnoreCase("kb")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024*1024*1024)+""));
                } else if (to.equalsIgnoreCase("mb")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024*1024)+""));
                } else if (to.equalsIgnoreCase("gb")) {
                    editto.setText((Float.parseFloat(fromvalue)*(1024)+""));
                } else if (to.equalsIgnoreCase("tb")) {
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("decimal")) {
                try {
                    if (to.equalsIgnoreCase("decimal")) {
                        editto.setText(fromvalue);
                    } else if (to.equalsIgnoreCase("octal")) {
                        editto.setText(Integer.toOctalString(Integer.parseInt(fromvalue)));
                    } else if (to.equalsIgnoreCase("binary")) {
                        editto.setText(Integer.toBinaryString(Integer.parseInt(fromvalue)));
                    } else if (to.equalsIgnoreCase("hexadecimal")) {
                        editto.setText(Integer.toHexString(Integer.parseInt(fromvalue)));
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    editto.setText("Error Input data");
                }
            } else if (from.equalsIgnoreCase("octal")) {
                try {
                    if (to.equalsIgnoreCase("decimal")) {
                        editto.setText(Integer.parseInt(fromvalue, 8) + "");
                    } else if (to.equalsIgnoreCase("octal")) {
                        editto.setText(fromvalue);
                    } else if (to.equalsIgnoreCase("binary")) {
                        editto.setText(Integer.toBinaryString(Integer.parseInt(fromvalue, 8)));
                    } else if (to.equalsIgnoreCase("hexadecimal")) {
                        editto.setText(Integer.toHexString(Integer.parseInt(fromvalue, 8)));
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    editto.setText("Error Input data");
                }
            } else if (from.equalsIgnoreCase("binary")) {
                try {
                    if (to.equalsIgnoreCase("decimal")) {
                        editto.setText(Integer.parseInt(fromvalue, 2) + "");
                    } else if (to.equalsIgnoreCase("octal")) {
                        editto.setText(Integer.toOctalString(Integer.parseInt(fromvalue, 2)));
                    } else if (to.equalsIgnoreCase("binary")) {
                        editto.setText(fromvalue);
                    } else if (to.equalsIgnoreCase("hexadecimal")) {
                        editto.setText(Integer.toHexString(Integer.parseInt(fromvalue, 2)));
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    editto.setText("Error Input data");
                }
            } else if (from.equalsIgnoreCase("hexadecimal")) {
                try {
                    if (to.equalsIgnoreCase("decimal")) {
                        editto.setText(Integer.parseInt(fromvalue, 16) + "");
                    } else if (to.equalsIgnoreCase("octal")) {
                        editto.setText(Integer.toOctalString(Integer.parseInt(fromvalue, 16)));
                    } else if (to.equalsIgnoreCase("binary")) {
                        editto.setText(Integer.toBinaryString(Integer.parseInt(fromvalue, 16)));
                    } else if (to.equalsIgnoreCase("hexadecimal")) {
                        editto.setText(fromvalue);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    editto.setText("Error Input data");
                }
            } else if (from.equalsIgnoreCase("square mm")) {
                if (to.equalsIgnoreCase("square mm")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("square cm")) {
                    editto.setText(((Float.parseFloat(fromvalue))/100)+"");
                } else if (to.equalsIgnoreCase("square mtr")) {
                    editto.setText(((Float.parseFloat(fromvalue))/1000000)+"");
                } else if (to.equalsIgnoreCase("square ft")) {
                    editto.setText(((Float.parseFloat(fromvalue))/92903)+"");
                } else if (to.equalsIgnoreCase("acre")) {
                    editto.setText(((Float.parseFloat(fromvalue))/4046856000.0)+"");
                } else if (to.equalsIgnoreCase("square km")) {
                    editto.setText(((Float.parseFloat(fromvalue))/1000000000000.0)+"");
                }
            } else if (from.equalsIgnoreCase("square cm")) {
                if (to.equalsIgnoreCase("square mm")) {
                    editto.setText(((Float.parseFloat(fromvalue))*100)+"");
                } else if (to.equalsIgnoreCase("square cm")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("square mtr")) {
                    editto.setText(((Float.parseFloat(fromvalue))/10000)+"");
                } else if (to.equalsIgnoreCase("square ft")) {
                    editto.setText(((Float.parseFloat(fromvalue))/929.03)+"");
                } else if (to.equalsIgnoreCase("acre")) {
                    editto.setText(((Float.parseFloat(fromvalue))/40468560)+"");
                } else if (to.equalsIgnoreCase("square km")) {
                    editto.setText(((Float.parseFloat(fromvalue))/10000000000.0)+"");
                }
            } else if (from.equalsIgnoreCase("square mtr")) {
                if (to.equalsIgnoreCase("square mm")) {
                    editto.setText(((Float.parseFloat(fromvalue))*1000000)+"");
                } else if (to.equalsIgnoreCase("square cm")) {
                    editto.setText(((Float.parseFloat(fromvalue))*10000)+"");
                } else if (to.equalsIgnoreCase("square mtr")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("square ft")) {
                    editto.setText(((Float.parseFloat(fromvalue))*10.764)+"");
                } else if (to.equalsIgnoreCase("acre")) {
                    editto.setText(((Float.parseFloat(fromvalue))/4046.856)+"");
                } else if (to.equalsIgnoreCase("square km")) {
                    editto.setText(((Float.parseFloat(fromvalue))/1000000)+"");
                }
            } else if (from.equalsIgnoreCase("square ft")) {
                if (to.equalsIgnoreCase("square mm")) {
                    editto.setText(((Float.parseFloat(fromvalue))*92903)+"");
                } else if (to.equalsIgnoreCase("square cm")) {
                    editto.setText(((Float.parseFloat(fromvalue))*929.03)+"");
                } else if (to.equalsIgnoreCase("square mtr")) {
                    editto.setText(((Float.parseFloat(fromvalue))/10.764)+"");
                } else if (to.equalsIgnoreCase("square ft")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("acre")) {
                    editto.setText(((Float.parseFloat(fromvalue))/43560)+"");
                } else if (to.equalsIgnoreCase("square km")) {
                    editto.setText(((Float.parseFloat(fromvalue))/10760000)+"");
                }
            } else if (from.equalsIgnoreCase("acre")) {
                if (to.equalsIgnoreCase("square mm")) {
                    editto.setText(((Float.parseFloat(fromvalue))*4046856000.0)+"");
                } else if (to.equalsIgnoreCase("square cm")) {
                    editto.setText(((Float.parseFloat(fromvalue))/40468560)+"");
                } else if (to.equalsIgnoreCase("square mtr")) {
                    editto.setText(((Float.parseFloat(fromvalue))/4046.856)+"");
                } else if (to.equalsIgnoreCase("square ft")) {
                    editto.setText(((Float.parseFloat(fromvalue))*43560)+"");
                } else if (to.equalsIgnoreCase("acre")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("square km")) {
                    editto.setText(((Float.parseFloat(fromvalue))/247.105)+"");
                }
            } else if (from.equalsIgnoreCase("square km")) {
                if (to.equalsIgnoreCase("square mm")) {
                    editto.setText(((Float.parseFloat(fromvalue))/1000000000000.0)+"");
                } else if (to.equalsIgnoreCase("square cm")) {
                    editto.setText(((Float.parseFloat(fromvalue))*10000000000.0)+"");
                } else if (to.equalsIgnoreCase("square mtr")) {
                    editto.setText(((Float.parseFloat(fromvalue))*1000000)+"");
                } else if (to.equalsIgnoreCase("square ft")) {
                    editto.setText(((Float.parseFloat(fromvalue))*10760000)+"");
                } else if (to.equalsIgnoreCase("acre")) {
                    editto.setText(((Float.parseFloat(fromvalue))*247.105)+"");
                } else if (to.equalsIgnoreCase("square km")) {
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("hz")) {
                if (to.equalsIgnoreCase("hz")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("mhz")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                } else if (to.equalsIgnoreCase("khz")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("rpm")) {
                    editto.setText((Float.parseFloat(fromvalue)*60)+"");
                }
            } else if (from.equalsIgnoreCase("mhz")) {
                if (to.equalsIgnoreCase("hz")){
                    editto.setText((Float.parseFloat(fromvalue)*1000000)+"");
                } else if (to.equalsIgnoreCase("mhz")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("khz")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("rpm")) {
                    editto.setText((Float.parseFloat(fromvalue)*60000000)+"");
                }
            } else if (from.equalsIgnoreCase("khz")) {
                if (to.equalsIgnoreCase("hz")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("mhz")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("khz")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("rpm")) {
                    editto.setText((Float.parseFloat(fromvalue)*60000)+"");
                }
            } else if (from.equalsIgnoreCase("rpm")) {
                if (to.equalsIgnoreCase("hz")) {
                    editto.setText((Float.parseFloat(fromvalue)/60)+"");
                } else if (to.equalsIgnoreCase("mhz")) {
                    editto.setText((Float.parseFloat(fromvalue)/60000000)+"");
                } else if (to.equalsIgnoreCase("khz")) {
                    editto.setText((Float.parseFloat(fromvalue)/60000)+"");
                } else if (to.equalsIgnoreCase("rpm")) {
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("ms")) {
                if (to.equalsIgnoreCase("ms")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("sec")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("min")) {
                    editto.setText((Float.parseFloat(fromvalue)/60000)+"");
                } else if (to.equalsIgnoreCase("hour")) {
                    editto.setText((Float.parseFloat(fromvalue)/3600000)+"");
                } else if (to.equalsIgnoreCase("day")) {
                    editto.setText((Float.parseFloat(fromvalue)/86400000)+"");
                } else if (to.equalsIgnoreCase("week")) {
                    editto.setText((Float.parseFloat(fromvalue)/604800000)+"");
                } else if (to.equalsIgnoreCase("year")){
                    editto.setText((Float.parseFloat(fromvalue)/31540000000.0)+"");
                }
            } else if (from.equalsIgnoreCase("sec")) {
                if (to.equalsIgnoreCase("ms")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("sec")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("min")) {
                    editto.setText((Float.parseFloat(fromvalue)/60)+"");
                } else if (to.equalsIgnoreCase("hour")) {
                    editto.setText((Float.parseFloat(fromvalue)/3600)+"");
                } else if (to.equalsIgnoreCase("day")) {
                    editto.setText((Float.parseFloat(fromvalue)/86400)+"");
                } else if (to.equalsIgnoreCase("week")) {
                    editto.setText((Float.parseFloat(fromvalue)/604800)+"");
                } else if (to.equalsIgnoreCase("year")){
                    editto.setText((Float.parseFloat(fromvalue)/31540000)+"");
                }
            } else if (from.equalsIgnoreCase("min")) {
                if (to.equalsIgnoreCase("ms")) {
                    editto.setText((Float.parseFloat(fromvalue)*60000)+"");
                } else if (to.equalsIgnoreCase("sec")) {
                    editto.setText((Float.parseFloat(fromvalue)*60)+"");
                } else if (to.equalsIgnoreCase("min")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("hour")) {
                    editto.setText((Float.parseFloat(fromvalue)/60)+"");
                } else if (to.equalsIgnoreCase("day")) {
                    editto.setText((Float.parseFloat(fromvalue)/1440)+"");
                } else if (to.equalsIgnoreCase("week")) {
                    editto.setText((Float.parseFloat(fromvalue)/10080)+"");
                } else if (to.equalsIgnoreCase("year")){
                    editto.setText((Float.parseFloat(fromvalue)/525600)+"");
                }
            } else if (from.equalsIgnoreCase("hour")) {
                if (to.equalsIgnoreCase("ms")) {
                    editto.setText((Float.parseFloat(fromvalue)*3600000)+"");
                } else if (to.equalsIgnoreCase("sec")) {
                    editto.setText((Float.parseFloat(fromvalue)*3600)+"");
                } else if (to.equalsIgnoreCase("min")) {
                    editto.setText((Float.parseFloat(fromvalue)*60)+"");
                } else if (to.equalsIgnoreCase("hour")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("day")) {
                    editto.setText((Float.parseFloat(fromvalue)/24)+"");
                } else if (to.equalsIgnoreCase("week")) {
                    editto.setText((Float.parseFloat(fromvalue)/168)+"");
                } else if (to.equalsIgnoreCase("year")){
                    editto.setText((Float.parseFloat(fromvalue)/87600)+"");
                }
            } else if (from.equalsIgnoreCase("day")) {
                if (to.equalsIgnoreCase("ms")) {
                    editto.setText((Float.parseFloat(fromvalue)*86400000)+"");
                } else if (to.equalsIgnoreCase("sec")) {
                    editto.setText((Float.parseFloat(fromvalue)*86400)+"");
                } else if (to.equalsIgnoreCase("min")) {
                    editto.setText((Float.parseFloat(fromvalue)*1440)+"");
                } else if (to.equalsIgnoreCase("hour")) {
                    editto.setText((Float.parseFloat(fromvalue)*24)+"");
                } else if (to.equalsIgnoreCase("day")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("week")) {
                    editto.setText((Float.parseFloat(fromvalue)/7)+"");
                } else if (to.equalsIgnoreCase("year")){
                    editto.setText((Float.parseFloat(fromvalue)/365)+"");
                }
            } else if (from.equalsIgnoreCase("week")) {
                if (to.equalsIgnoreCase("ms")) {
                    editto.setText((Float.parseFloat(fromvalue)*604800000)+"");
                } else if (to.equalsIgnoreCase("sec")) {
                    editto.setText((Float.parseFloat(fromvalue)*604800)+"");
                } else if (to.equalsIgnoreCase("min")) {
                    editto.setText((Float.parseFloat(fromvalue)*10080)+"");
                } else if (to.equalsIgnoreCase("hour")) {
                    editto.setText((Float.parseFloat(fromvalue)*168)+"");
                } else if (to.equalsIgnoreCase("day")) {
                    editto.setText((Float.parseFloat(fromvalue)*7)+"");
                } else if (to.equalsIgnoreCase("week")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("year")){
                    editto.setText((Float.parseFloat(fromvalue)/52.143)+"");
                }
            } else if (from.equalsIgnoreCase("year")) {
                if (to.equalsIgnoreCase("ms")) {
                    editto.setText((Float.parseFloat(fromvalue)*31540000000.0)+"");
                } else if (to.equalsIgnoreCase("sec")) {
                    editto.setText((Float.parseFloat(fromvalue)*31540000)+"");
                } else if (to.equalsIgnoreCase("min")) {
                    editto.setText((Float.parseFloat(fromvalue)*525600)+"");
                } else if (to.equalsIgnoreCase("hour")) {
                    editto.setText((Float.parseFloat(fromvalue)*87600)+"");
                } else if (to.equalsIgnoreCase("day")) {
                    editto.setText((Float.parseFloat(fromvalue)*365)+"");
                } else if (to.equalsIgnoreCase("week")) {
                    editto.setText((Float.parseFloat(fromvalue)*52.143)+"");
                } else if (to.equalsIgnoreCase("year")){
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("ug")) {
                if (to.equalsIgnoreCase("ug")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("mg")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("g")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                } else if (to.equalsIgnoreCase("kg")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000000)+"");
                } else if (to.equalsIgnoreCase("lbs(pound)")) {
                    editto.setText((Float.parseFloat(fromvalue)/453600000)+"");
                } else if (to.equalsIgnoreCase("oz")) {
                    editto.setText((Float.parseFloat(fromvalue)/28350000)+"");
                } else if (to.equalsIgnoreCase("tonne")){
                    editto.setText((Float.parseFloat(fromvalue)/1000000000000.0)+"");
                }
            } else if (from.equalsIgnoreCase("mg")) {
                if (to.equalsIgnoreCase("ug")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("mg")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("g")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("kg")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                } else if (to.equalsIgnoreCase("lbs(pound)")) {
                    editto.setText((Float.parseFloat(fromvalue)/453592.37)+"");
                } else if (to.equalsIgnoreCase("oz")) {
                    editto.setText((Float.parseFloat(fromvalue)/28349.523)+"");
                } else if (to.equalsIgnoreCase("tonne")){
                    editto.setText((Float.parseFloat(fromvalue)/1000000000)+"");
                }
            } else if (from.equalsIgnoreCase("g")) {
                if (to.equalsIgnoreCase("ug")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000)+"");
                } else if (to.equalsIgnoreCase("mg")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("g")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("kg")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("lbs(pound)")) {
                    editto.setText((Float.parseFloat(fromvalue)/453.592)+"");
                } else if (to.equalsIgnoreCase("oz")) {
                    editto.setText((Float.parseFloat(fromvalue)/28.35)+"");
                } else if (to.equalsIgnoreCase("tonne")){
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                }
            } else if (from.equalsIgnoreCase("kg")) {
                if (to.equalsIgnoreCase("ug")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000000)+"");
                } else if (to.equalsIgnoreCase("mg")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000)+"");
                } else if (to.equalsIgnoreCase("g")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("kg")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("lbs(pound)")) {
                    editto.setText((Float.parseFloat(fromvalue)*2.205)+"");
                } else if (to.equalsIgnoreCase("oz")) {
                    editto.setText((Float.parseFloat(fromvalue)*35.274)+"");
                } else if (to.equalsIgnoreCase("tonne")){
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                }
            } else if (from.equalsIgnoreCase("lbs(pound)")) {
                if (to.equalsIgnoreCase("ug")) {
                    editto.setText((Float.parseFloat(fromvalue)*453600000)+"");
                } else if (to.equalsIgnoreCase("mg")) {
                    editto.setText((Float.parseFloat(fromvalue)*453592.37)+"");
                } else if (to.equalsIgnoreCase("g")) {
                    editto.setText((Float.parseFloat(fromvalue)*453.592)+"");
                } else if (to.equalsIgnoreCase("kg")) {
                    editto.setText((Float.parseFloat(fromvalue)/2.205)+"");
                } else if (to.equalsIgnoreCase("lbs(pound)")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("oz")) {
                    editto.setText((Float.parseFloat(fromvalue)*16)+"");
                } else if (to.equalsIgnoreCase("tonne")){
                    editto.setText((Float.parseFloat(fromvalue)/2204.623)+"");
                }
            } else if (from.equalsIgnoreCase("oz")) {
                if (to.equalsIgnoreCase("ug")) {
                    editto.setText((Float.parseFloat(fromvalue)/28350000)+"");
                } else if (to.equalsIgnoreCase("mg")) {
                    editto.setText((Float.parseFloat(fromvalue)*28349.523)+"");
                } else if (to.equalsIgnoreCase("g")) {
                    editto.setText((Float.parseFloat(fromvalue)*28.35)+"");
                } else if (to.equalsIgnoreCase("kg")) {
                    editto.setText((Float.parseFloat(fromvalue)/35.274)+"");
                } else if (to.equalsIgnoreCase("lbs(pound)")) {
                    editto.setText((Float.parseFloat(fromvalue)/16)+"");
                } else if (to.equalsIgnoreCase("oz")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("tonne")){
                    editto.setText((Float.parseFloat(fromvalue)/35273.962)+"");
                }
            } else if (from.equalsIgnoreCase("tonne")) {
                if (to.equalsIgnoreCase("ug")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000000000.0)+"");
                } else if (to.equalsIgnoreCase("mg")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000000)+"");
                } else if (to.equalsIgnoreCase("g")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000)+"");
                } else if (to.equalsIgnoreCase("kg")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("lbs(pound)")) {
                    editto.setText((Float.parseFloat(fromvalue)*2204.623)+"");
                } else if (to.equalsIgnoreCase("oz")) {
                    editto.setText((Float.parseFloat(fromvalue)*35273.962)+"");
                } else if (to.equalsIgnoreCase("tonne")){
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("m/s")) {
                if (to.equalsIgnoreCase("m/s")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("ft/s")) {
                    editto.setText((Float.parseFloat(fromvalue)*3.281)+"");
                } else if (to.equalsIgnoreCase("km/h")) {
                    editto.setText((Float.parseFloat(fromvalue)*3.6)+"");
                } else if (to.equalsIgnoreCase("mi/h(mph)")) {
                    editto.setText((Float.parseFloat(fromvalue)*2.237)+"");
                } else if (to.equalsIgnoreCase("mach")) {
                    editto.setText((Float.parseFloat(fromvalue)/343)+"");
                }
            } else if (from.equalsIgnoreCase("ft/s")) {
                if (to.equalsIgnoreCase("m/s")) {
                    editto.setText((Float.parseFloat(fromvalue)/3.281)+"");
                } else if (to.equalsIgnoreCase("ft/s")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("km/h")) {
                    editto.setText((Float.parseFloat(fromvalue)*1.097)+"");
                } else if (to.equalsIgnoreCase("mi/h(mph)")) {
                    editto.setText((Float.parseFloat(fromvalue)/1.467)+"");
                } else if (to.equalsIgnoreCase("mach")) {
                    editto.setText((Float.parseFloat(fromvalue)/1125.328)+"");
                }
            } else if (from.equalsIgnoreCase("km/h")) {
                if (to.equalsIgnoreCase("m/s")) {
                    editto.setText((Float.parseFloat(fromvalue)/3.6)+"");
                } else if (to.equalsIgnoreCase("ft/s")) {
                    editto.setText((Float.parseFloat(fromvalue)/1.097)+"");
                } else if (to.equalsIgnoreCase("km/h")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("mi/h(mph)")) {
                    editto.setText((Float.parseFloat(fromvalue)/1.609)+"");
                } else if (to.equalsIgnoreCase("mach")) {
                    editto.setText((Float.parseFloat(fromvalue)/1234.8)+"");
                }
            } else if (from.equalsIgnoreCase("mi/h(mph)")) {
                if (to.equalsIgnoreCase("m/s")) {
                    editto.setText((Float.parseFloat(fromvalue)/2.237)+"");
                } else if (to.equalsIgnoreCase("ft/s")) {
                    editto.setText((Float.parseFloat(fromvalue)*1.467)+"");
                } else if (to.equalsIgnoreCase("km/h")) {
                    editto.setText((Float.parseFloat(fromvalue)*1.609)+"");
                } else if (to.equalsIgnoreCase("mi/h(mph)")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("mach")) {
                    editto.setText((Float.parseFloat(fromvalue)/767.269)+"");
                }
            } else if (from.equalsIgnoreCase("mach")) {
                if (to.equalsIgnoreCase("m/s")) {
                    editto.setText((Float.parseFloat(fromvalue)*343)+"");
                } else if (to.equalsIgnoreCase("ft/s")) {
                    editto.setText((Float.parseFloat(fromvalue)*1125.328)+"");
                } else if (to.equalsIgnoreCase("km/h")) {
                    editto.setText((Float.parseFloat(fromvalue)*1234.8)+"");
                } else if (to.equalsIgnoreCase("mi/h(mph)")) {
                    editto.setText((Float.parseFloat(fromvalue)*767.269)+"");
                } else if (to.equalsIgnoreCase("mach")) {
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("um")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText((Float.parseFloat(fromvalue)/10000)+"");
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText((Float.parseFloat(fromvalue)/25400)+"");
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText((Float.parseFloat(fromvalue)/304800)+"");
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText((Float.parseFloat(fromvalue)/914400)+"");
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText((Float.parseFloat(fromvalue)/1829000)+"");
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText((Float.parseFloat(fromvalue)/1609000000)+"");
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText((Float.parseFloat(fromvalue)/1000000000)+"");
                }
            } else if (from.equalsIgnoreCase("mm")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText((Float.parseFloat(fromvalue)/10)+"");
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText((Float.parseFloat(fromvalue)/25.4)+"");
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText((Float.parseFloat(fromvalue)/304.8)+"");
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText((Float.parseFloat(fromvalue)/914.4)+"");
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText((Float.parseFloat(fromvalue)/1828.8)+"");
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText((Float.parseFloat(fromvalue)/1609000)+"");
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                }
            } else if (from.equalsIgnoreCase("cm")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText((Float.parseFloat(fromvalue)*10000)+"");
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*10)+"");
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText((Float.parseFloat(fromvalue)/100)+"");
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText((Float.parseFloat(fromvalue)/2.54)+"");
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText((Float.parseFloat(fromvalue)/30.48)+"");
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText((Float.parseFloat(fromvalue)/91.44)+"");
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText((Float.parseFloat(fromvalue)/182.88)+"");
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText((Float.parseFloat(fromvalue)/160934.4)+"");
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText((Float.parseFloat(fromvalue)/100000)+"");
                }
            } else if (from.equalsIgnoreCase("m")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText((Float.parseFloat(fromvalue)/100)+"");
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText((Float.parseFloat(fromvalue)*39.37)+"");
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText((Float.parseFloat(fromvalue)*3.281)+"");
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText((Float.parseFloat(fromvalue)*1.094)+"");
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText((Float.parseFloat(fromvalue)/1.829)+"");
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText((Float.parseFloat(fromvalue)/1609.344)+"");
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                }
            } else if (from.equalsIgnoreCase("inch")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText((Float.parseFloat(fromvalue)*25400)+"");
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*25.4)+"");
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText((Float.parseFloat(fromvalue)*2.54)+"");
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText((Float.parseFloat(fromvalue)/39.37)+"");
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText((Float.parseFloat(fromvalue)/12)+"");
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText((Float.parseFloat(fromvalue)/36)+"");
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText((Float.parseFloat(fromvalue)/72)+"");
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText((Float.parseFloat(fromvalue)/63360)+"");
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText((Float.parseFloat(fromvalue)/39370.079)+"");
                }
            } else if (from.equalsIgnoreCase("ft")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText((Float.parseFloat(fromvalue)*304800)+"");
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*304.8)+"");
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText((Float.parseFloat(fromvalue)*30.48)+"");
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText((Float.parseFloat(fromvalue)/3.281)+"");
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText((Float.parseFloat(fromvalue)*12)+"");
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText((Float.parseFloat(fromvalue)/3)+"");
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText((Float.parseFloat(fromvalue)/6)+"");
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText((Float.parseFloat(fromvalue)/5280)+"");
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText((Float.parseFloat(fromvalue)*3280.84)+"");
                }
            } else if (from.equalsIgnoreCase("yd")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText((Float.parseFloat(fromvalue)*914400)+"");
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*914.4)+"");
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText((Float.parseFloat(fromvalue)*91.44)+"");
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText((Float.parseFloat(fromvalue)/1.094)+"");
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText((Float.parseFloat(fromvalue)*36)+"");
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText((Float.parseFloat(fromvalue)*3)+"");
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText((Float.parseFloat(fromvalue)/2)+"");
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText((Float.parseFloat(fromvalue)/1760)+"");
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText((Float.parseFloat(fromvalue)/1093.613)+"");
                }
            } else if (from.equalsIgnoreCase("fathom")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText((Float.parseFloat(fromvalue)*1829000)+"");
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*1828.8)+"");
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText((Float.parseFloat(fromvalue)*182.88)+"");
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText((Float.parseFloat(fromvalue)*1.829)+"");
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText((Float.parseFloat(fromvalue)*72)+"");
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText((Float.parseFloat(fromvalue)*6)+"");
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText((Float.parseFloat(fromvalue)*2)+"");
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText((Float.parseFloat(fromvalue)/880)+"");
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText((Float.parseFloat(fromvalue)/546.807)+"");
                }
            } else if (from.equalsIgnoreCase("mile")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText((Float.parseFloat(fromvalue)*1609000000)+"");
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*1609000)+"");
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText((Float.parseFloat(fromvalue)*160934.4)+"");
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText((Float.parseFloat(fromvalue)*1609.344)+"");
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText((Float.parseFloat(fromvalue)*63360)+"");
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText((Float.parseFloat(fromvalue)*5280)+"");
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText((Float.parseFloat(fromvalue)*1760)+"");
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText((Float.parseFloat(fromvalue)*880)+"");
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText((Float.parseFloat(fromvalue)*1.609)+"");
                }
            } else if (from.equalsIgnoreCase("km")) {
                if (to.equalsIgnoreCase("um")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000000)+"");
                } else if (to.equalsIgnoreCase("mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000)+"");
                } else if (to.equalsIgnoreCase("cm")) {
                    editto.setText((Float.parseFloat(fromvalue)*100000)+"");
                } else if (to.equalsIgnoreCase("m")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("inch")) {
                    editto.setText((Float.parseFloat(fromvalue)*39370.079)+"");
                } else if (to.equalsIgnoreCase("ft")){
                    editto.setText((Float.parseFloat(fromvalue)*3280.84)+"");
                } else if (to.equalsIgnoreCase("yd")){
                    editto.setText((Float.parseFloat(fromvalue)*1093.613)+"");
                } else if (to.equalsIgnoreCase("fathom")){
                    editto.setText((Float.parseFloat(fromvalue)*546.807)+"");
                } else if (to.equalsIgnoreCase("mile")){
                    editto.setText((Float.parseFloat(fromvalue)/1.609)+"");
                } else if (to.equalsIgnoreCase("km")){
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("ml")) {
                if (to.equalsIgnoreCase("ml")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("L(litre)")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("cubic mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("cubic cm")) {
                    editto.setText((Float.parseFloat(fromvalue))+"");
                } else if (to.equalsIgnoreCase("cubic mtr")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                } else if (to.equalsIgnoreCase("gal")){
                    editto.setText((Float.parseFloat(fromvalue)/4546.09)+"");
                }
            } else if (from.equalsIgnoreCase("L(litre)")) {
                if (to.equalsIgnoreCase("ml")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("L(litre)")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("cubic mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000)+"");
                } else if (to.equalsIgnoreCase("cubic cm")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("cubic mtr")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("gal")){
                    editto.setText((Float.parseFloat(fromvalue)/4.546)+"");
                }
            } else if (from.equalsIgnoreCase("cubic mm")) {
                if (to.equalsIgnoreCase("ml")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("L(litre)")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                } else if (to.equalsIgnoreCase("cubic mm")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("cubic cm")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("cubic mtr")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000000)+"");
                } else if (to.equalsIgnoreCase("gal")){
                    editto.setText((Float.parseFloat(fromvalue)/4546090)+"");
                }
            } else if (from.equalsIgnoreCase("cubic cm")) {
                if (to.equalsIgnoreCase("ml")) {
                    editto.setText((Float.parseFloat(fromvalue))+"");
                } else if (to.equalsIgnoreCase("L(litre)")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000)+"");
                } else if (to.equalsIgnoreCase("cubic mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("cubic cm")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("cubic mtr")) {
                    editto.setText((Float.parseFloat(fromvalue)/1000000)+"");
                } else if (to.equalsIgnoreCase("gal")){
                    editto.setText((Float.parseFloat(fromvalue)/4546.09)+"");
                }
            } else if (from.equalsIgnoreCase("cubic mtr")) {
                if (to.equalsIgnoreCase("ml")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000)+"");
                } else if (to.equalsIgnoreCase("L(litre)")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000)+"");
                } else if (to.equalsIgnoreCase("cubic mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000000)+"");
                } else if (to.equalsIgnoreCase("cubic cm")) {
                    editto.setText((Float.parseFloat(fromvalue)*1000000)+"");
                } else if (to.equalsIgnoreCase("cubic mtr")) {
                    editto.setText(fromvalue);
                } else if (to.equalsIgnoreCase("gal")){
                    editto.setText((Float.parseFloat(fromvalue)*219.969)+"");
                }
            } else if (from.equalsIgnoreCase("gal")) {
                if (to.equalsIgnoreCase("ml")) {
                    editto.setText((Float.parseFloat(fromvalue)*4546.09)+"");
                } else if (to.equalsIgnoreCase("L(litre)")) {
                    editto.setText((Float.parseFloat(fromvalue)*4.546)+"");
                } else if (to.equalsIgnoreCase("cubic mm")) {
                    editto.setText((Float.parseFloat(fromvalue)*4546090)+"");
                } else if (to.equalsIgnoreCase("cubic cm")) {
                    editto.setText((Float.parseFloat(fromvalue)*4546.09)+"");
                } else if (to.equalsIgnoreCase("cubic mtr")) {
                    editto.setText((Float.parseFloat(fromvalue)/219.969)+"");
                } else if (to.equalsIgnoreCase("gal")){
                    editto.setText(fromvalue);
                }
            } else if (from.equalsIgnoreCase("USD")) {
                currencyusd(fromvalue);
            } else if (from.equalsIgnoreCase("INR")) {
                currencyinr(fromvalue);
            } else if (from.equalsIgnoreCase("EUR")) {
                currencyeur(fromvalue);
            } else if (from.equalsIgnoreCase("JPY")) {
                currencyjpy(fromvalue);
            } else if (from.equalsIgnoreCase("GBP")) {
                currencygbp(fromvalue);
            } else if (from.equalsIgnoreCase("CHF")) {
                currencychf(fromvalue);
            } else if (from.equalsIgnoreCase("AUD")) {
                currencyaud(fromvalue);
            } else if (from.equalsIgnoreCase("CAD")) {
                currencycad(fromvalue);
            }
        }
        else
        {
            editto.setText("");
        }
    }
}
