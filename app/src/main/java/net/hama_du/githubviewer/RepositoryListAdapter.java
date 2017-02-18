package net.hama_du.githubviewer;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.hama_du.githubviewer.model.Repository;

/**
 * Created by hama_du on 2017/02/18.
 */

public class RepositoryListAdapter extends ArrayAdapter<Repository> {
    private LayoutInflater layoutInflater;

    public RepositoryListAdapter(Context context) {
        super(context, 0);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.application_list_item_card, parent, false);
        }

        Repository info = getItem(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.title);
        TextView subTextView = (TextView) convertView.findViewById(R.id.sub);

        titleTextView.setText(info.name);
        subTextView.setText("star:" + info.star);

        return convertView;
    }
}
