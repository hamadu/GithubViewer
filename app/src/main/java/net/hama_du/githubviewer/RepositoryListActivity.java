package net.hama_du.githubviewer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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
import net.hama_du.githubviewer.request.RepositoryRequest;
import net.hama_du.githubviewer.request.RequestHandler;

public class RepositoryListActivity extends AppCompatActivity {
    private ListView listView;

    private RepositoryListAdapter adapter;

    private RequestHandler requestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);

        requestHandler = RequestHandler.getInstance(getApplicationContext());

        listView = (ListView) findViewById(R.id.repository_list);
        listView.setDivider(null);
        adapter = new RepositoryListAdapter(getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                Repository repo = (Repository) listView.getItemAtPosition(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repo.url));
                getApplicationContext().startActivity(browserIntent);
            }
        });
    }

    private void fetchRepositories(String query) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Searching Repositories...");
        progressDialog.show();

        StringRequest request = RepositoryRequest.get(
                query,
                new Response.Listener<RepositorySearchResponse>() {
                    @Override
                    public void onResponse(RepositorySearchResponse response) {
                        adapter.clear();
                        adapter.addAll(response.repositories);
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("rsr", error.toString());
                        progressDialog.dismiss();
                    }
                }
        );
        requestHandler.addToRequestQueue(request);
    }

    public void searchRepositories(View view) {
        EditText editText = (EditText) findViewById(R.id.query);
        fetchRepositories(editText.getText().toString());
    }
}
