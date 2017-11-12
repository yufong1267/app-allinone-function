package tw.com.flag.allinone;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 2017/11/12.
 */

public class DataBase extends AppCompatActivity {

    public static SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE,null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + "test" + "(item VARCHAR(32))";
        db.execSQL(createTable);
        final Activity activity = this;

        super.onCreate(savedInstanceState);
    }



/**
*這邊放入  放入資料庫的方法
 */
    public static void addData(String item)
    {
        ContentValues cv = new ContentValues(1);
        cv.put("item" , item);
        db.insert("test" , null , cv);
    }
}
