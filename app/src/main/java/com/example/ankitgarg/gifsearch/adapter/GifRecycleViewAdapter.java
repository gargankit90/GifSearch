package com.example.ankitgarg.gifsearch.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.ankitgarg.gifsearch.R;
import com.example.ankitgarg.gifsearch.model.ImageModel;
import com.example.ankitgarg.gifsearch.network.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by ankitgarg on 3/6/16.
 */
public class GifRecycleViewAdapter extends RecyclerView.Adapter<GifRecycleViewAdapter.ViewHolder> {
    ImageRequest imageRequest;
    private ArrayList<ImageModel> mDataset;

    public GifRecycleViewAdapter(ArrayList<ImageModel> mDataset) {
        this.mDataset = mDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public pl.droidsonroids.gif.GifImageView gifImageView;
        public ViewHolder(View view) {
            super(view);
            gifImageView = (pl.droidsonroids.gif.GifImageView)view.findViewById(R.id.gifImageViewItem);
        }
    }



    @Override
    public GifRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_search_result_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final GifRecycleViewAdapter.ViewHolder holder, int position) {
        holder.gifImageView.setImageDrawable(null);
        imageRequest = new ImageRequest(mDataset.get(position).getUrl(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.gifImageView.setImageBitmap(response);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error","Cant download image");
            }
        });
        VolleySingleton.getInstance().getRequestQueue().add(imageRequest);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
