package com.example.ankitgarg.gifsearch.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ankitgarg.gifsearch.Constants;
import com.example.ankitgarg.gifsearch.R;
import com.example.ankitgarg.gifsearch.adapter.GifRecycleViewAdapter;
import com.example.ankitgarg.gifsearch.model.ImageModel;
import com.example.ankitgarg.gifsearch.network.VolleySingleton;
import com.example.ankitgarg.gifsearch.ui.SearchSuggestionDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SearchActivity extends AppCompatActivity implements SearchSuggestionDialog.onItemSearch {

//    @Bind(R.id.activitySearchEt)
//    EditText activitySearchEt;
    SearchSuggestionDialog searchSuggestionDialog;
    private boolean isDialogShown = false;
    FragmentManager fragmentManager;
    @Bind(R.id.gifRecycleView)
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private GifRecycleViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onSearchItem(String searchTxt) {
        // Take the search text and make network calls for it.
        removeDialog();
        String url = Constants.GIPHY_URL + "?q=" +searchTxt + "&api_key=" +Constants.GIPHY_API_KEY;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,(String)null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    ArrayList<ImageModel> imgModels = ImageModel.parseJSONObjects(response);
                    Toast.makeText(SearchActivity.this,"Length :"+imgModels.size(), Toast.LENGTH_LONG).show();
                    mAdapter = new GifRecycleViewAdapter(imgModels);
                    recyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchActivity.this,error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance().getRequestQueue().add(jsonObjectRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.miSearch:
                fragmentManager = getSupportFragmentManager();
                if (searchSuggestionDialog == null) {
                    searchSuggestionDialog = SearchSuggestionDialog.newInstance();
                }
                if (searchSuggestionDialog != null && isDialogShown == false) {
                    searchSuggestionDialog.show(fragmentManager, SearchSuggestionDialog.TAG);
                    isDialogShown = true;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void removeDialog(){
        searchSuggestionDialog.dismiss();
        isDialogShown = false;
    }
}