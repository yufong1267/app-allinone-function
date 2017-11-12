package tw.com.flag.allinone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class Page2 extends AppCompatActivity {

    //定義一個手勢識別器
    private  GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);


        //--------------------動作愈設
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //--------------------

        detector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){

            //當手指在上面滑動的時候會條用
            @Override
            public boolean onFling(MotionEvent e1 , MotionEvent e2 , float velocityX , float velocityY)
            {
                if((e2.getRawX() - e1.getRawX()) > 200)
                {
                    //顯示上一個頁面

                    return true;
                }
                if ((e1.getRawX() - e2.getRawX()) > 200)
                {
                    //顯示下一個頁面
                    showNext();
                    return true;
                }

                return super.onFling(e1,e2,velocityX,velocityY);
            }
        });


    }


    public void next(View view)
    {
        showNext();
    }

    private void showNext() {
        Intent intent = new Intent(this,Page3.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.tran_in , R.anim.tran_out);
    }

    //使用手勢識別器
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /**
     *這邊加入 refresh function
     */
//

    public void refresh(View view)
    {

        SQLiteDatabase db;
        db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE,null);
        boolean check001 = false;
        boolean check002 = false;






        Cursor c =db.rawQuery("SELECT * FROM test" , null);

        if(c.moveToFirst())
        {
            do {
                   if(c.getString(0).equals("item001"))
                   {
                       Button btn = (Button)findViewById(R.id.mp1);
                       btn.setVisibility(View.VISIBLE);
                   }
                   if(c.getString(0).equals("item002"))
                   {
                       Button btn = (Button)findViewById(R.id.mp2);
                       btn.setVisibility(View.VISIBLE);
                   }
            }while(c.moveToNext());

        }else{
        if(check001)
        {
            Button btn = (Button)findViewById(R.id.mp1);
            btn.setVisibility(View.VISIBLE);
        }
        if(check002)
        {
            Button btn = (Button)findViewById(R.id.mp2);
            btn.setVisibility(View.VISIBLE);
        }
    }
    }



}
