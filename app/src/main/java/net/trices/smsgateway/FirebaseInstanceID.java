package net.trices.smsgateway;

import android.Manifest;
import android.net.Uri;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.permissioneverywhere.PermissionEverywhere;
import com.permissioneverywhere.PermissionResponse;
import com.permissioneverywhere.PermissionResultCallback;

public class FirebaseInstanceID extends FirebaseMessagingService {

    private final String SERVER = "http://atifnaseem22.000webhostapp.com/reg_device.php";
    private final int REQ_CODE_PERMISSION_SEND_SMS = 221;

    @Override
    public void onNewToken(String s) {
        registerToken(s);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String phoneNum = remoteMessage.getData().get("phone");
        String msg = remoteMessage.getData().get("msg");


        PermissionEverywhere.getPermission(getApplicationContext(),
                new String[]{Manifest.permission.SEND_SMS},
                REQ_CODE_PERMISSION_SEND_SMS,
                "Notification title",
                "This app needs a write permission",
                R.mipmap.ic_launcher)
                .enqueue(new PermissionResultCallback() {
                    @Override
                    public void onComplete(PermissionResponse permissionResponse) {
                    }
                });


        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNum, null, msg,
                null, null);
    }




    public void registerToken(String token){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = SERVER + "?Token=" + Uri.encode(token);

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
