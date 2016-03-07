package com.example.ankitgarg.gifsearch.ui;

/**
 * Created by ankitgarg on 3/6/16.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.ankitgarg.gifsearch.R;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import co.moonmonkeylabs.realmsearchview.ClearableEditText;
import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;

public class CustomSearchView extends LinearLayout {
    private RealmRecyclerView realmRecyclerView;
    private ClearableEditText searchBar;
    private RealmSearchAdapter adapter;
    private boolean addFooterOnIdle;
    private Handler handler = null;

    public CustomSearchView(Context context) {
        super(context);
        this.init(context, (AttributeSet)null);
    }

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    public CustomSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.realm_search_view, this);
        this.setOrientation(VERTICAL);
        this.realmRecyclerView = (RealmRecyclerView)this.findViewById(co.moonmonkeylabs.realmsearchview.R.id.realm_recycler_view);
        this.searchBar = (ClearableEditText)this.findViewById(co.moonmonkeylabs.realmsearchview.R.id.search_bar);
        this.initAttrs(context, attrs);
        this.searchBar.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if(s.length() == 0 ){
                    CustomSearchView.this.realmRecyclerView.setAdapter(null);
                }else if(s.length() >= 1){
                    CustomSearchView.this.realmRecyclerView.setAdapter(adapter);
                }
                CustomSearchView.this.adapter.filter(s.toString());
                CustomSearchView.this.addFooterHandler(s.toString());
            }
        });
    }

    private void addFooterHandler(final String search) {
        if(this.addFooterOnIdle) {
            if(this.handler == null) {
                this.adapter.removeFooter();
                this.handler = new Handler();
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        if(search.equals(CustomSearchView.this.searchBar.getText().toString())) {
                            CustomSearchView.this.adapter.addFooter();
                        }

                        CustomSearchView.this.handler = null;
                    }
                }, 300L);
            }
        }
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, co.moonmonkeylabs.realmsearchview.R.styleable.RealmSearchView);
        int hintTextResId = typedArray.getResourceId(co.moonmonkeylabs.realmsearchview.R.styleable.RealmSearchView_rsvHint, co.moonmonkeylabs.realmsearchview.R.string.rsv_default_search_hint);
        this.searchBar.setHint(hintTextResId);
        int clearDrawableResId = typedArray.getResourceId(co.moonmonkeylabs.realmsearchview.R.styleable.RealmSearchView_rsvClearDrawable, -1);
        if(clearDrawableResId != -1) {
            this.searchBar.setClearDrawable(this.getResources().getDrawable(clearDrawableResId));
        }

        this.addFooterOnIdle = typedArray.getBoolean(co.moonmonkeylabs.realmsearchview.R.styleable.RealmSearchView_rsvAddFooter, false);
        typedArray.recycle();
    }

    public void setAdapter(RealmSearchAdapter adapter) {
        this.adapter = adapter;
        this.adapter.filter("");
    }

    public String getSearchBarText() {
        return this.searchBar.getText().toString();
    }
}

