package net.hama_du.githubviewer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.hama_du.githubviewer.model.Repository;
import net.hama_du.githubviewer.model.RepositorySearchResponse;

public class RepositoryListActivity extends AppCompatActivity {
    private ListView listView;

    private RepositoryListAdapter adapter;

    private RequestQueue requestQueue;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        requestQueue = Volley.newRequestQueue(this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        listView = (ListView) findViewById(R.id.repository_list);
        listView.setDivider(null);
        adapter = new RepositoryListAdapter(getApplicationContext());
        listView.setAdapter(adapter);
    }

    private void fetchRepositories(String query) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(
                Request.Method.GET, "https://api.github.com/search/repositories?q=" + query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        RepositorySearchResponse repositorySearchResponse = gson.fromJson(response, RepositorySearchResponse.class);

                        adapter.clear();
                        adapter.addAll(repositorySearchResponse.repositories);

                        progressDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("rsr", error.toString());
                        progressDialog.hide();
                    }
                }
        );
        requestQueue.add(request);
    }

    public void searchRepositories(View view) {
        EditText editText = (EditText) findViewById(R.id.query);
        fetchRepositories(editText.getText().toString());
    }
}
