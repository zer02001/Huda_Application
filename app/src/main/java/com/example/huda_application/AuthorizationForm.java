package com.example.huda_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huda_application.user.PatientFormData;

import java.util.regex.Pattern;

public class AuthorizationForm extends AppCompatActivity implements View.OnClickListener
{

    private PatientFormData data;
    private TextView PersonalRepresentative;

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^((0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])-(19|2[0-9])[0-9]{2})$"); // date pattern match

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization_form);
        Intent extras = getIntent();

        if (extras != null)
        {
            data =  (PatientFormData)  extras.getSerializableExtra("patientdata");
        }
        //Log.i("info  ", "The user name in the application is   " + lastNameTxt + firstNameTxt );
        PersonalRepresentative = (TextView) findViewById(R.id.nextForm3);
        PersonalRepresentative.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.nextForm3)
        {
            final EditText patientSigned3 = findViewById(R.id.patientNamePrinted3);
            final EditText patientSig3 = findViewById(R.id.patientNameSignature3);
            final EditText consentDateSign3 = findViewById(R.id.consentFormDate3);

            final String patientSignedText3 = patientSigned3.getText().toString().trim();
            final String patientSignatureText3 = patientSig3.getText().toString().trim();
            final String consentDateTxt3 = consentDateSign3.getText().toString().trim();

            if(TextUtils.isEmpty(consentDateTxt3))
            {
                Toast.makeText(AuthorizationForm.this, "Date cannot be empty", Toast.LENGTH_LONG).show();
                consentDateSign3.setError("Date is required");
                consentDateSign3.requestFocus();
            }
            else if(!DATE_PATTERN.matcher(consentDateTxt3).matches())
            {
                Toast.makeText(AuthorizationForm.this, "Date format MM-DD-YYYY", Toast.LENGTH_LONG).show();
                consentDateSign3.setError("Date format is required");
                consentDateSign3.requestFocus();
            }
            else if(TextUtils.isEmpty(patientSignedText3) || patientSignedText3.length() > 30)
            {
                Toast.makeText(AuthorizationForm.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
                patientSigned3.setError("Signed name is required");
                patientSigned3.requestFocus();
            }
            else if(TextUtils.isEmpty(patientSignatureText3) || patientSignatureText3.length() > 30)
            {
                Toast.makeText(AuthorizationForm.this, "Signature cannot be empty", Toast.LENGTH_LONG).show();
                patientSig3.setError("Signature is required");
                patientSig3.requestFocus();
            }
            else
            {
                Intent PersonalRepresentative = new Intent (AuthorizationForm.this, PersonalRepresentative.class);
                PersonalRepresentative.putExtra("patientdata",data);
                startActivity(PersonalRepresentative);
            }

        }

    }
}