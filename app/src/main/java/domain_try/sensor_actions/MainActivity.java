package domain_try.sensor_actions;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import DBUtils.Database_main;
import DatabaseTables.Users;


public class MainActivity extends AppCompatActivity {

    static TextView view4;
    static EditText usernameView;
    static EditText passwordView;
    static EditText nameView;
    static EditText amountView;
    static TextView userlabel;
    static TextView passlabel;
    static Button b1;
    static Button b2;
    static Thread t1;
    static Spinner sp;
    static Users user =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sp= (Spinner)findViewById(R.id.spinner);
        view4 =(TextView)findViewById(R.id.textView4);
        usernameView = (EditText)findViewById(R.id.editText);
        passwordView = (EditText)findViewById(R.id.editText2);

        SensorManager mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        MotionDetector md = new MotionDetector(mSensorManager);


        //create the Database
        Database_main.dbmgr = new DBUtils.Db_Manager(this);

        //getConnection
        ConnectionSource con = Database_main.getConnectionSource();

        b2 = (Button) findViewById(R.id.button2);

        // Capture button clicks, for Sign UP, switch the Activity
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity
                Intent myIntent = new Intent(MainActivity.this,
                        SignUpActivity.class);
                startActivity(myIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add("SignOut");
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
    @Override
    protected void onDestroy() {
        //can do some stuff like closing connections
        super.onDestroy();
    }

    //hide virtual keyboard
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
    public static boolean findUser()
    {
        String uname =usernameView.getText().toString();
        String pass = passwordView.getText().toString();
        if(SignIn.authenticate(uname, pass))
        {
            view4.setText("authenticated");
            return true;
        }
        return false;
    }
    public void on_login(View v)
    {
        if(findUser())
        {
            Intent myIntent = new Intent(MainActivity.this,
                    ShowProfile.class);
            startActivity(myIntent);
        }
        else
        {
            view4.setText("Invalid Credentials");
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                    INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }


    }

}
