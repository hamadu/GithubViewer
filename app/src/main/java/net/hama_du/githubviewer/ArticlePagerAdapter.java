package net.hama_du.githubviewer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hama_du on 2017/02/18.
 */

public class ArticlePagerAdapter extends FragmentPagerAdapter {
    private List<SparseArrayCompat<String>> mList;

    public ArticlePagerAdapter(FragmentManager fm) {
        super(fm);
        mList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        SparseArrayCompat<String> item = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putInt("page", position);
        bundle.putString("color", item.get(0));
        bundle.putString("name", item.get(1));
        bundle.putString("description", item.get(2));

        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    public void add(SparseArrayCompat<String> item) {
        mList.add(item);
    }

    public void addAll(List<SparseArrayCompat<String>> list) {
        mList.addAll(list);
    }
}
