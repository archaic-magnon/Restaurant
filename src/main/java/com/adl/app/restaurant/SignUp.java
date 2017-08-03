package com.adl.app.restaurant;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<String> sArrayAdapter;
    String[] salutations = {"Mr.","Miss","Mrs."};

    String firstName;
    String middleName;
    String lastName;
    String email;
    String mobileNumber;
    String dOB;
    String annvDate;
    String mac;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

       /* spinner= (Spinner) findViewById(R.id.spinner);
        sArrayAdapter=new ArrayAdapter<String>(this,R.layout.spinner_layout, salutations);
        spinner.setAdapter(sArrayAdapter);
*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            //toolbar backbutton implemntation
        }
        return super.onOptionsItemSelected(item);
    }

    public void getDataFromForm()
    {
        firstName=((TextView)  findViewById(R.id.firstname)).getText().toString();
        middleName=((TextView)  findViewById(R.id.middlename)).getText().toString();
        lastName=((TextView)  findViewById(R.id.lastname)).getText().toString();
        email=((TextView)  findViewById(R.id.email)).getText().toString();
        mobileNumber=((TextView)  findViewById(R.id.mobile)).getText().toString();
        dOB=((TextView)  findViewById(R.id.dob)).getText().toString();
        annvDate=((TextView)  findViewById(R.id.anv)).getText().toString();

        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        mac = info.getMacAddress();

        Toast.makeText(getApplicationContext(), mac, Toast.LENGTH_LONG).show();









    }

}
