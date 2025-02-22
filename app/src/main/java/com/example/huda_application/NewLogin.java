package com.example.huda_application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class NewLogin extends AppCompatActivity implements View.OnClickListener {

    TextView login, signUp;
    TextView PrimaryCare_Button, MentalHealth_Button, VisionCare_Button, NewPatients_Button, DentalCare_Button, SpecialtyCare_Button;
    private Button language_Button;
    public AlertDialog menu;
    Spinner ServicesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Saved_language();
        setContentView(R.layout.activity_new_login);
                                                                          // Class that will show the Main page of the application with the buttons that
        // show the health services for the clinic
        login = (TextView) findViewById(R.id.LoginButton);
        signUp = (TextView) findViewById(R.id.SignUpButton);

        login.setOnClickListener((View.OnClickListener) this);
        signUp.setOnClickListener((View.OnClickListener) this);

        PrimaryCare_Button = (TextView) findViewById(R.id.PrimaryHeader);
        MentalHealth_Button = (TextView) findViewById(R.id.MentalHeader);
        VisionCare_Button = (TextView) findViewById(R.id.VisionHeader);
        DentalCare_Button = (TextView) findViewById(R.id.DentalHeader);
        SpecialtyCare_Button = (TextView) findViewById(R.id.SpecialtyHeader);               //The buttons that are used for the specific health Service for the application
        PrimaryCare_Button.setOnClickListener(this);
        MentalHealth_Button.setOnClickListener(this);
        VisionCare_Button.setOnClickListener(this);
        DentalCare_Button.setOnClickListener(this);
        SpecialtyCare_Button.setOnClickListener(this);
        final String[] langs_options = {"English", "عربي" , "español", "français", "اردو" , "বাংলা"};

        AlertDialog.Builder options = new AlertDialog.Builder(NewLogin.this);
        options.setTitle("Languages");
        language_Button = findViewById(R.id.language2);
        language_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                                    // Used to change the language in the application

                options.setSingleChoiceItems(langs_options, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface Diag_options, int opts ) {

                        switch(opts) {

                            case (0):
                                picklanguage("en");
                                recreate();
                                Toast.makeText(NewLogin.this,"Current Language: English",Toast.LENGTH_SHORT).show();
                                break;

                            case(1):

                                picklanguage("ar");
                                recreate();
                                Toast.makeText(NewLogin.this,"اللغة الحالية: العربية",Toast.LENGTH_SHORT).show();
                                break;
                            case(2):

                                picklanguage("es");
                                recreate();
                                Toast.makeText(NewLogin.this,"Idioma actual: español",Toast.LENGTH_SHORT).show();
                                break;
                            case(3):

                                picklanguage("fr");
                                recreate();
                                Toast.makeText(NewLogin.this,"Langue actuelle: français",Toast.LENGTH_SHORT).show();
                                break;

                            case(4):

                                picklanguage("ur");
                                recreate();
                                Toast.makeText(NewLogin.this,"موجودہ زبان: اردو",Toast.LENGTH_SHORT).show();
                                break;
                            case(5):

                                picklanguage("bn");
                                recreate();
                                Toast.makeText(NewLogin.this,"বর্তমান ভাষা: বাংলা",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        Diag_options.dismiss();
                    }
                });
                create_menu(options);
            }
        });

    }

    private void picklanguage(String l)
    {
        SharedPreferences.Editor Saver = getSharedPreferences("langauge", MODE_MULTI_PROCESS).edit();
        Locale language_option =  new Locale(l);
        DisplayMetrics metrics =  getBaseContext().getResources().getDisplayMetrics();                  // Picks the locale after the language is picked from the dialog
        language_swtich(l, metrics, language_option);

        Saver.putString("prev_language" ,l);
        Saver.apply();
    }



    public void Saved_language(){
        SharedPreferences saved_language =getSharedPreferences("langauge", MODE_MULTI_PROCESS);
        picklanguage(saved_language.getString("prev_language" , ""));
        // choose the saved language from the application
    }
    public void create_menu(AlertDialog.Builder opts){

        menu = opts.create();
        menu.show();
    }


    public void language_swtich(String l , DisplayMetrics m , Locale lang) {

        Locale.setDefault(lang);

        Configuration page = new Configuration();                                                   // Switch the language
        page.locale = lang;

        getBaseContext().getResources().updateConfiguration(page, m);

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.LoginButton) {
            Intent LoginPage = new Intent(this, MainActivity.class);
            startActivity(LoginPage);
        } else if (view.getId() == R.id.SignUpButton) {
            Intent RegPage = new Intent(this, RegisterAccount.class);
            startActivity(RegPage);
        }
        else if (view.getId() == R.id.PrimaryHeader) {
            Intent Primary = new Intent(this, PrimaryServices.class);
            startActivity(Primary);
        }                                                                           // On click for all the buttons that are located on the page of the application
        else if (view.getId() == R.id.MentalHeader) {
            Intent Mental = new Intent(this, MentalHealth.class);
            startActivity(Mental);
        }
        else if (view.getId() == R.id.VisionHeader) {
            Intent Vision = new Intent(this, VisionHealth.class);
            startActivity(Vision);
        }
//
        else if (view.getId() == R.id.DentalHeader) {
            Intent Dental = new Intent(this, DentalHealth.class);
            startActivity(Dental);
        }
        else if (view.getId() == R.id.SpecialtyHeader) {
            Intent Specialty = new Intent(this, SpecialtyHealth.class);
            startActivity(Specialty);
        }
    }
}