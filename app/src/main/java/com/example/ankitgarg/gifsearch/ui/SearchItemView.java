package com.example.ankitgarg.gifsearch.ui;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ankitgarg.gifsearch.R;
import com.example.ankitgarg.gifsearch.model.SearchTerm;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ankitgarg on 3/5/16.
 */
public class SearchItemView extends RelativeLayout {
    @Bind(R.id.searchTv)
    TextView searchTxt;

    public SearchItemView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.search_item_view, this);
        ButterKnife.bind(this);
    }

    public void bind(SearchTerm searchTerm) {
        searchTxt.setText(searchTerm.getSearchTerm());
    }
}
