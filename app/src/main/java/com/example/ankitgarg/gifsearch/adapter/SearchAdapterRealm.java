package com.example.ankitgarg.gifsearch.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.ankitgarg.gifsearch.model.SearchTerm;
import com.example.ankitgarg.gifsearch.ui.SearchItemView;

import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import co.moonmonkeylabs.realmsearchview.RealmSearchViewHolder;
import io.realm.Realm;

/**
 * Created by ankitgarg on 3/5/16.
 */
public class SearchAdapterRealm extends RealmSearchAdapter<SearchTerm, SearchAdapterRealm.ViewHolder> {
    private HandleClickListener mClickListener = null;
    public SearchAdapterRealm(Context context, Realm realm, String filterKey, HandleClickListener listener) {
        super(context, realm, filterKey);
        this.mClickListener = listener;
    }

    public class ViewHolder extends RealmSearchViewHolder {
        private SearchItemView searchItemView;
        public ViewHolder(SearchItemView searchItemView) {
            super(searchItemView);
            this.searchItemView = searchItemView;
        }
    }

    public interface HandleClickListener {
        public void onSearchItemClick(String searchTxt);
    }



    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder vh = new ViewHolder(new SearchItemView(viewGroup.getContext()));
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(SearchAdapterRealm.ViewHolder viewHolder, final int position) {
        final SearchTerm searchTerm = realmResults.get(position);
        viewHolder.searchItemView.bind(searchTerm);
        viewHolder.searchItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onSearchItemClick(searchTerm.getSearchTerm());
            }
        });
    }

}
