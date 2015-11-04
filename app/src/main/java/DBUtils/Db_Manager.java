package DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by himanshu on 10/24/2015.
 */
public class Db_Manager extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "user_info.db";


    public Db_Manager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //called only once when database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        //can create tables here

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b) {
        // db.execSQL(SQL_DELETE_ENTRIES);
        // onCreate(db);
    }

    public void insert_Row(SQLiteDatabase db, String word, String description) {
        ContentValues values = new ContentValues();
        values.put("KEY_WORD", word);
        values.put("KEY_DEFINITION", description);
      //  db.insert(DICTIONARY_TABLE_NAME, null, values);
    }

}


