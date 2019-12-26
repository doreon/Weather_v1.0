package ru.sibgu.doreon.coursework.code;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.*;

public class Functions {

    private JSONObject json;

    public JSONObject request_task(final String url, final String payload ) {
        Thread task = new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                try {
                    Request request = new Request.Builder()
                            .url(payload == null ? url : url + payload)
                            .build();
                    Response response = client.newCall(request).execute();
                    json = new JSONObject(Objects.requireNonNull(response.body()).string());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        task.setName("GET Thread Task");
        task.start();
        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return json;
    }

}
