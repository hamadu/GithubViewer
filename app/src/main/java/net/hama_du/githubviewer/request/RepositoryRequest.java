package net.hama_du.githubviewer.request;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.hama_du.githubviewer.model.RepositorySearchResponse;

/**
 * Created by hama_du on 2017/02/18.
 */

public class RepositoryRequest  {
    private static Gson gson;

    public synchronized static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson;
    }

    public static StringRequest get(String query, final Response.Listener<RepositorySearchResponse> listener, final Response.ErrorListener errorListener) {
        return new StringRequest(
            Request.Method.GET, "https://api.github.com/search/repositories?q=" + query,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    RepositorySearchResponse repositorySearchResponse = getGson().fromJson(response, RepositorySearchResponse.class);
                    listener.onResponse(repositorySearchResponse);
                }
            },
            errorListener
        );
    }
}
