package DBUtils;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import DBUtils.Db_Manager;

/**
 * Created by himanshu on 10/24/2015.
 */
public class Database_main {
    public static Db_Manager dbmgr= null;
    public static SQLiteDatabase getDatabase()
    {
       return  dbmgr.getWritableDatabase();
    }
    public static Db_Manager getDatabaseObject()
    {
        if(dbmgr == null)
            return null;
        return dbmgr;

    }

    public static ConnectionSource getConnectionSource()
    {
        ConnectionSource connectionSource =
                new AndroidConnectionSource(getDatabase());
        return connectionSource;
    }

}
