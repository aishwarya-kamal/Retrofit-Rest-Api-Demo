package com.platinumstudio.retrofitsample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.platinumstudio.retrofitsample.R;
import com.platinumstudio.retrofitsample.adapter.CustomAdapter;
import com.platinumstudio.retrofitsample.model.Photo;
import com.platinumstudio.retrofitsample.network.GetDataService;
import com.platinumstudio.retrofitsample.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        /** Call the method with parameter in the interface to get the data*/
        Call<List<Photo>> call = service.getAllPhotos();

        /** Enqueue() asynchronously sends the request and notifies your app with a
         * callback when a response comes back.*/
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            /** onResponse() is invoked for a received HTTP response. This method is called for a response
             * that can be correctly handled even if the server returns an error message. So if you
             * get a status code of 404 or 500, this method will still be called. To get the status
             * code in order for you to handle situations based on them, you can use the method
             * response.code(). You can also use the isSuccessful() method to find out if the status
             * code is in the range 200-300, indicating success.
             */
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                generateDataList(response.body());
            }

            @Override
            /** onFailure(): invoked when a network exception occurred communicating to the server or
             when an unexpected exception occurred handling the request or processing the response.
             */
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Photo> photoList){

        // Initialize the RecyclerView.
        recyclerView = findViewById(R.id.recycler_view);

        // Set the Layout Manager.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and set it to the RecyclerView.
        adapter = new CustomAdapter(photoList, this);
        recyclerView.setAdapter(adapter);
    }
}
