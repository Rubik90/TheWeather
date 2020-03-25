package com.is2.theweather;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.is2.theweather.models.Coord;
import com.is2.theweather.models.Main;
import com.is2.theweather.models.Sys;
import com.is2.theweather.models.Wind;
import com.is2.theweather.retrofit.WeatherAPI;
import com.is2.theweather.models.Response;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;

import static com.is2.theweather.retrofit.WeatherAPI.API_KEY;
import static com.is2.theweather.retrofit.WeatherAPI.UNITS_METRIC;

public class MainActivity extends AppCompatActivity {

    private CardView cardView;
    public EditText city_name;
    private TextView meteo;
    private TextView data_ora;
    private TextView temperature;
    private TextView wind;
    private TextView sys;
    private TextView main;
    private TextView coord;
    Button nextButton;

    public class ac {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        String aa = "测试";
        nextButton = findViewById(R.id.next);
        nextButton.setVisibility(View.INVISIBLE);

        cardView = findViewById(R.id.cardView);
        city_name = findViewById(R.id.city_name);
        meteo = findViewById(R.id.meteo);
        data_ora = findViewById(R.id.data_ora);
        temperature = findViewById(R.id.temperature);
        wind = findViewById(R.id.wind);
        sys = findViewById(R.id.sys);
        main = findViewById(R.id.main);
        coord = findViewById(R.id.coord);
        nextButton = findViewById(R.id.next);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},1);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case (R.id.about):
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                break;

            case (R.id.quit):
                setResult(123);
                this.finish();
                break;

        }

        return super.onOptionsItemSelected(item);

    }

    @SuppressLint("StaticFieldLeak")
    public class getWeather extends AsyncTask<Response, Void, Response> {

        @Override
        protected Response doInBackground(Response... responses) {
            return responses[0];
        }

        @Override
        protected void onPostExecute(Response weatherResponse) {

            super.onPostExecute(weatherResponse);

            cardView.setVisibility(View.VISIBLE);

            if (weatherResponse!=null){

                cardView.setVisibility(View.VISIBLE);
                meteo.setVisibility(View.VISIBLE);
                data_ora.setVisibility(View.VISIBLE);
                temperature.setVisibility(View.VISIBLE);
                wind.setVisibility(View.VISIBLE);
                sys.setVisibility(View.VISIBLE);
                main.setVisibility(View.VISIBLE);
                coord.setVisibility(View.VISIBLE);
                wind.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);

                //si inseriscono i dati negli opportuni campi
                data_ora.setText(getDateCurrentTimeZone(weatherResponse.getDt()));
                wind.setText(weatherResponse.getWind().toString());
                sys.setText(weatherResponse.getSys().toString());
                main.setText(weatherResponse.getMain().toString());
                coord.setText(weatherResponse.getCoord().toString());
                String desc = weatherResponse.getWeather().get(0).getDescription();
                meteo.setText(desc);
                String temp = weatherResponse.getMain().getTemp() + " \u2103";
                temperature.setText(temp);

            }

            else

                {

                cardView.setVisibility(View.VISIBLE);
                meteo.setText(getResources().getString(R.string.no_city));

                //se la API CALL non va a buon fine tutti i campi restano invisibili
                meteo.setVisibility(View.INVISIBLE);
                data_ora.setVisibility(View.INVISIBLE);
                temperature.setVisibility(View.INVISIBLE);
                wind.setVisibility(View.INVISIBLE);
                sys.setVisibility(View.INVISIBLE);
                main.setVisibility(View.INVISIBLE);
                coord.setVisibility(View.INVISIBLE);
                wind.setVisibility(View.INVISIBLE);
                meteo.setVisibility(View.VISIBLE);

                }

        }

    }

    public void checkWeather(View view) {

        EditText editText = findViewById(R.id.city_name);
        String cityname = editText.getText().toString();

        if (cityname.isEmpty()) {

            //se la città inserita è vuota i campi restano invisibili
            cardView.setVisibility(View.VISIBLE);
            data_ora.setVisibility(View.INVISIBLE);
            temperature.setVisibility(View.INVISIBLE);
            wind.setVisibility(View.INVISIBLE);
            sys.setVisibility(View.INVISIBLE);
            main.setVisibility(View.INVISIBLE);
            coord.setVisibility(View.INVISIBLE);
            wind.setVisibility(View.INVISIBLE);
            meteo.setText(getResources().getString(R.string.edittext_above));
        } else {

            WeatherAPI.Factory.getInstance().getWeather(cityname, API_KEY, UNITS_METRIC).enqueue(new Callback<Response>() {

                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    new getWeather().execute(response.body());
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }

            });
        }
    }

    public void NextActivity(View view) {

        String text = "In " + city_name.getText().toString() + " the weather is " + meteo.getText().toString() + " measured on " +
                      data_ora.getText().toString() + ", the temperature is " + temperature.getText().toString() +
                      ", wind: " + wind.getText().toString()+ ", " + sys.getText().toString()+ " main values" + main.getText().toString()+
                      ", geographic coordinates " + coord.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("toShare", text);
        startActivity(intent);
        String s ="This is lint string!";

    }

    public  String getDateCurrentTimeZone(long timestamp) {

        SimpleDateFormat mytime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return mytime.format(timestamp*1000);

    }

}