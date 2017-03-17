package com.beginner.micromaster.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText Name_ET;
    private EditText LastName_ET;
    private EditText Email_ET;
    private EditText Number_ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get EditText references
        Name_ET= (EditText) findViewById(R.id.name_in);
        LastName_ET = (EditText) findViewById(R.id.lastName_in);
        Email_ET = (EditText) findViewById(R.id.email_in);
        Number_ET = (EditText) findViewById(R.id.number_input);

        Button button = (Button) findViewById(R.id.btn_newContact);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String name = Name_ET.getText().toString();
                String lastName = LastName_ET.getText().toString();
                String email = Email_ET.getText().toString();
                String number = Number_ET.getText().toString();

                //Get values from EditText and create a new Contact
                Integer number_int = parsePhoneNumber(number);
                if (number_int != null) {
                    createNewContact(name, lastName, email, number_int);
                }
            }
        });
    }

    private void createNewContact(String name, String lastName, String email, Integer number) {
        Contact contact = new Contact(name, lastName, email, number);
        Log.d(TAG, "New contact created: " + contact.toString());
        clearEditText();

        Intent intent = new Intent(this, DetailActivity.class);
        //TODO: send the rest of contact parameters
        intent.putExtra("name", name);
        intent.putExtra("lastName", lastName);
        intent.putExtra("email", email);
        intent.putExtra("Number", number);
        startActivity(intent);
    }

    private void clearEditText() {
        Name_ET.setText("");
        LastName_ET.setText("");
        Email_ET.setText("");
        Number_ET.setText("");
    }

    public static Integer parsePhoneNumber(String phoneNumber) {
        try {
            return Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
