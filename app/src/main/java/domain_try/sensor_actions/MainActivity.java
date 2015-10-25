package domain_try.sensor_actions;

import android.database.Cursor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.EasyEditSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBUtils.Database_main;
import DatabaseTables.Users;
import domain_try.sensor_actions.MotionDetector;
public class MainActivity extends AppCompatActivity {

    static EditText view1;
    static EditText view2;
    static EditText view3;
    static EditText view4;
    static Thread t1;
    static Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1= (EditText)findViewById(R.id.editText);
        view2= (EditText)findViewById(R.id.editText2);
        view3= (EditText)findViewById(R.id.editText3);
        view4= (EditText)findViewById(R.id.editText4);
        sp= (Spinner)findViewById(R.id.spinner);
        SensorManager mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        MotionDetector md = new MotionDetector(mSensorManager);

        //create the Database
        Database_main.dbmgr = new DBUtils.Db_Manager(this);

        //getConnection
        ConnectionSource con = Database_main.getConnectionSource();

        //create the table
        try {
                    Dao<Users, String> userdao =
                            DaoManager.createDao(con, Users.class);
                    if(!userdao.isTableExists())
                    {
                        TableUtils.createTable(con, Users.class);

                        Users usr = new Users("Himanshu", "123", 1000);
                    Users usr2 = new Users("Manu", "123", 1000);
                    Users usr3 = new Users("Himanshu1", "123", 1000);
                    Users usr4= new Users("Manu1", "123", 1000);
                    Users usr5 = new Users("Himanshu2", "123", 1000);
                    userdao.create(usr);
                    userdao.create(usr2);userdao.create(usr4);
                    userdao.create(usr3);userdao.create(usr5);

                }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        //createDAO - Database Access Object and User object(new row for table)
        try {
                Dao<Users, String> userdao =
                    DaoManager.createDao(con, Users.class);
                    List<Users> allusers = userdao.queryForAll();
            ArrayAdapter<String> aArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
            sp.setAdapter(aArrayAdapter);
            ArrayAdapter adp =(ArrayAdapter)sp.getAdapter();
            for(Users u:allusers)
            {
                adp.add(u.getName());
            }

        } catch (SQLException e) {
            view4.setText("Exception Caught"+e.getMessage());
            try {
                con.close();
                } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        t1 = new Thread(md);
        t1.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
    public static EditText getView1()
    {
        return view1;
    }
    public static EditText getView2()
    {
        return view2;
    }
    public static EditText getView3()
    {
        return view3;
    }
    public static EditText getView4()
    {
        return view4;
    }



}
