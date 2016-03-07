package com.example.ankitgarg.gifsearch.ui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ankitgarg.gifsearch.GifSearchApplication;
import com.example.ankitgarg.gifsearch.R;
import com.example.ankitgarg.gifsearch.Utility;
import com.example.ankitgarg.gifsearch.adapter.SearchAdapterRealm;

import butterknife.ButterKnife;

public class SearchSuggestionDialog extends DialogFragment implements SearchAdapterRealm.HandleClickListener {
    public static final String TAG = "SearchSuggestionDialog";
    onItemSearch mCallback;
    CustomSearchView realmSearchView;
    SearchAdapterRealm mAdapter;

    public SearchSuggestionDialog() {
        // Required empty public constructor
    }

    public static SearchSuggestionDialog newInstance() {
        SearchSuggestionDialog frag = new SearchSuggestionDialog();
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_suggestion, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this.getActivity());
        realmSearchView = (CustomSearchView) view.findViewById(R.id.search_view);
        mAdapter = new SearchAdapterRealm(this.getActivity(), GifSearchApplication.getmRealm(), "searchTerm", this);
        realmSearchView.setAdapter(mAdapter);
        Utility.showKeyboard(this.getActivity());
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (onItemSearch) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onSearchItemClick(String searchTxt) {
        mCallback.onSearchItem(searchTxt);
//        getDialog().dismiss();
    }

    public interface onItemSearch {
        public void onSearchItem(String searchTxt);
    }
}
