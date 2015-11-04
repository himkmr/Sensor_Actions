package domain_try.sensor_actions;

import android.content.Intent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    static EditText view4;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sp= (Spinner)findViewById(R.id.spinner);
        view4 =(EditText)findViewById(R.id.editText4);

        SensorManager mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        MotionDetector md = new MotionDetector(mSensorManager);


        //create the Database
        Database_main.dbmgr = new DBUtils.Db_Manager(this);

        //getConnection
        ConnectionSource con = Database_main.getConnectionSource();

        b2 = (Button) findViewById(R.id.button2);

        // Capture button clicks
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        SignUpActivity.class);
                startActivity(myIntent);
            }
        });



//
//        //createDAO - Database Access Object and User object(new row for table)
//        try {
//                Dao<Users, String> userdao =
//                    DaoManager.createDao(con, Users.class);
//
//                List<Users> allusers = userdao.queryForAll(); //get all the Users
//                //ArrayAdapter for Spinner
//                ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
//                sp.setAdapter(arrayadapter);
//                ArrayAdapter adp =(ArrayAdapter)sp.getAdapter();
//                for(Users u:allusers)
//                {
//                    adp.add(u.getName());
//                }
//
//        } catch (SQLException e) {
//            view4.setText("Exception Caught"+e.getMessage());
//            try {
//                    con.close();
//                } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        }
//        t1 = new Thread(md);
//        t1.start();

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

    public static EditText getView4()
    {
        return view4;
    }
    public static void findUser()
    {
        String uname =usernameView.getText().toString();
        String pass = passwordView.getText().toString();
        if(SignIn.authenticate(uname, pass))
            view4.setText("authenticated");
    }


}
