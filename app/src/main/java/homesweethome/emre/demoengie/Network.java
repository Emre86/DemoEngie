package homesweethome.emre.demoengie;

import android.util.Log;


import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by emre on 07/05/17.
 */
public class Network {

    private final String TAG = "Network";
    private int responseCode;


    public String postData(User user){
        OkHttpClient client = new OkHttpClient();

        String urlPost = "http://jsonplaceholder.typicode.com/users";
        String json= user.toString();
        Log.d(TAG, user.toString());
        MediaType JSON= MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, json);
        Log.d(TAG,body.toString());

        Request request = new Request.Builder()
                .url(urlPost)
                .post(body)
                .build();

        int httpStatusCode = -1;
        String res = null;

        try {
            Log.d(TAG, "AVANT lE RESPONSE");
            Log.d(TAG,request.toString());
            Log.d(TAG,request.body().toString());
            Response response = client.newCall(request).execute();//you have your response code
            Log.d(TAG,""+response.isSuccessful());
            Log.d(TAG, "APRES le RESPONSE");
            httpStatusCode=response.code();
            Log.d(TAG,""+httpStatusCode);
            res=response.body().string();
            Log.d(TAG,"Retour "+res);
            response.body().close();
        }
        catch (IOException ioe){
            Log.e(TAG,"IOException ioe");
        }
        res = ""+httpStatusCode+ " " + res;
        return res;
    }


}
