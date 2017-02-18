package net.hama_du.githubviewer;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hama_du on 2017/02/18.
 */

public class RequestController extends Application {
    public static final String TAG = RequestController.class
            .getSimpleName();

    private RequestQueue requestQueue;

    private static RequestController self;

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
    }

    public static synchronized RequestController getInstance() {
        return self;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        addToRequestQueue(req, TAG);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
