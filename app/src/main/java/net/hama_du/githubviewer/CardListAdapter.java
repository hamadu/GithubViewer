package net.hama_du.githubviewer;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hama_du on 2017/02/18.
 */

public class CardListAdapter extends ArrayAdapter<PackageInfo> {
    LayoutInflater mInflater;
    PackageManager packageManager;

    public CardListAdapter(Context context) {
        super(context, 0);
        mInflater = LayoutInflater.from(context);
        packageManager = context.getPackageManager();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.application_list_item_card, parent, false);
        }

        PackageInfo info = getItem(position);

        TextView tv = (TextView) convertView.findViewById(R.id.title);
        tv.setText(info.applicationInfo.loadLabel(packageManager));

        tv = (TextView) convertView.findViewById(R.id.sub);
        tv.setText(info.packageName + "\n" + "versionName : " + info.versionName + "\nversionCode : " + info.versionCode);

        ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
        iv.setImageDrawable(info.applicationInfo.loadIcon(packageManager));


        return convertView;
    }
}
