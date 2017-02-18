package net.hama_du.githubviewer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int page = bundle.getInt("page");
        String color = bundle.getString("color");
        String name = bundle.getString("name");
        String description = bundle.getString("description");

        View layout = inflater.inflate(R.layout.fragment_article, container, false);
        layout.setBackgroundColor(Color.parseColor(color));

        // 色名
        TextView nameView = (TextView) layout.findViewById(R.id.name_view);
        nameView.setText(name);
        // 説明 (色名の由来)
        TextView textView = (TextView) layout.findViewById(R.id.text_view);
        textView.setText(description);
        // 色コード
        TextView colorView = (TextView) layout.findViewById(R.id.color_view);
        colorView.setText(color);
        // ページ
        TextView pageView = (TextView) layout.findViewById(R.id.page_view);
        pageView.setText(String.valueOf(page));

        // Inflate the layout for this fragment
        return layout;
    }
}
