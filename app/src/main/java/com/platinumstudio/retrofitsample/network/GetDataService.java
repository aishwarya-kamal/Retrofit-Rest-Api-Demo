package com.platinumstudio.retrofitsample.network;

import com.platinumstudio.retrofitsample.model.Photo;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/** The endpoints are defined inside of an interface using special retrofit annotations to encode
 details about the parameters and request method.
 An endpoint is simply the URL we want to retrieve some information from, which in this
 instance is https://jsonplaceholder.typicode.com/photos.
 Each endpoint is represented as a method, which must include at least one HTTP annotation
 indicating how this request should be handled.
 */

public interface GetDataService {
    @GET("/photos")
    Call<List<Photo>> getAllPhotos();
}