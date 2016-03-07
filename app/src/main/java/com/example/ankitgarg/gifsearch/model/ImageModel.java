package com.example.ankitgarg.gifsearch.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ankitgarg on 2/29/16.
 */
public class ImageModel {
    private String url;
    private static ArrayList<ImageModel> imageModels;

    public ImageModel(String url) {
        this.url = url;
    }

    public static ArrayList<ImageModel> parseJSONObjects(JSONObject response) throws JSONException {
        ArrayList<ImageModel> responseImageModels = new ArrayList<ImageModel>();
        JSONArray imagesArr = response.getJSONArray("data");
        int len= imagesArr.length();
        JSONObject imgObj;
        ImageModel imgModel;
        for(int i=0; i<len; i++){
            imgObj = (JSONObject)imagesArr.get(i);
            imgObj = (JSONObject)imgObj.get("images");
            imgObj = (JSONObject)imgObj.get("fixed_height_downsampled");
            imgModel = new ImageModel((String)imgObj.get("url"));
            responseImageModels.add(imgModel);
        }
        imageModels = responseImageModels;
        return responseImageModels;
    }

    public String getUrl() {
        return url;
    }
}
