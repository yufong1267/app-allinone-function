package tw.com.flag.allinone;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.w3c.dom.Text;



public class Page3 extends AppCompatActivity {
    private GestureDetector detector;
    public SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * database preset
         */
        db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE,null);
        String createTable = "CREATE TABLE IF NOT EXISTS " + "test" + "(item VARCHAR(32))";
        db.execSQL(createTable);



        //----------------動作愈設
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //--------------------------------

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        detector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){

            //當手指在上面滑動的時候會條用
            @Override
            public boolean onFling(MotionEvent e1 , MotionEvent e2 , float velocityX , float velocityY)
            {
                if((e2.getRawX() - e1.getRawX()) > 200)
                {
                    //顯示上一個頁面
                    showPre();
                    return true;
                }
                if ((e1.getRawX() - e2.getRawX()) > 200)
                {
                    //顯示下一個頁面
                    shownext();
                    return true;
                }

                return super.onFling(e1,e2,velocityX,velocityY);
            }
        });
    }

    public void next(View view){
        shownext();
    }

    private void shownext() {
        Intent intent = new Intent(this,Page4.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.tran_in,R.anim.tran_out);
    }

    public void Pre(View view)
    {
        showPre();
    }

    private void showPre() {
        Intent intent = new Intent(this,Page2.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.tran_in,R.anim.tran_out);
    }

    //使用手勢識別器
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    //QRcode method
    public void onClick(View v) {


        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scanning...");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
//                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
//                integrator.setPrompt("Scan");
//                integrator.setCameraId(0);
//                integrator.setBeepEnabled(false);
//                integrator.setBarcodeImageEnabled(true);
//                integrator.initiateScan();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "QAQ沒有獲得項目", Toast.LENGTH_LONG).show();
            } else {
                String log = result.getContents().toString();

                Toast.makeText(this, "恭喜獲得: " + result.getContents(), Toast.LENGTH_LONG).show();
                addData(log);
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void addData(String item)
    {
        ContentValues cv = new ContentValues(1);
        cv.put("item" , item);
        db.insert("test" , null , cv);
    }
}
