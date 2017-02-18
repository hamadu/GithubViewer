package net.hama_du.githubviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {
    private ListView listView;
    private TextView titleTextView;
    private String[] names = { "a", "B", "cc" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        List<String> lnln = new ArrayList<>();
        for (String a : names) {
            lnln.add(a);
        }
        lnln.add(message);
        String[] listItems = new String[lnln.size()];
        for (int i = 0 ; i < lnln.size() ; i++) {
            listItems[i] = lnln.get(i);
        }

        listView = (ListView) findViewById(R.id.sample_list);
        titleTextView = (TextView) findViewById(R.id.title_sample_list_text);
//        if (savedInstanceState == null) {
//            listView = (ListView) findViewById(R.id.sample_list);
//            titleTextView = (TextView) findViewById(R.id.title_sample_list_text);
//        }
        listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listItems));
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                String item = (String) listView.getItemAtPosition(position);
                titleTextView.setText(item);
            }
        });
    }
}
