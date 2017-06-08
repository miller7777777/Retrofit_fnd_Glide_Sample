package android2.levelup.ru.lesson08_refactoring_and_retrofit;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarListFragment extends Fragment implements CarsAdapter.CarClickListener {
    private static final String TAG = CarListFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    public CarListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // load cars.json
        loadCars();

    }

    private void loadCars() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://goo.gl")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CarsApi carsApi = retrofit.create(CarsApi.class);
        carsApi.getCars().enqueue(new retrofit2.Callback<CarsResponse>() {
            @Override
            public void onResponse(retrofit2.Call<CarsResponse> call, retrofit2.Response<CarsResponse> response) {
                if (response.isSuccessful()) {
                    CarsResponse carsResponse = response.body();
                    RequestManager imageRequestManager = Glide.with(getContext());
                    recyclerView.setAdapter(new CarsAdapter(carsResponse.getCars(), imageRequestManager, CarListFragment.this));
                }
            }

            @Override
            public void onFailure(retrofit2.Call<CarsResponse> call, Throwable t) {
                Log.w(TAG, "Failed to load cars", t);
                Toast.makeText(getContext(), "Failed connection", Toast.LENGTH_LONG).show();
            }
        });

//        OkHttpClient client = new OkHttpClient();
//        final Request request = new Request.Builder().url("https://goo.gl/1tW53X").build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.w(TAG, "Failed to load cars", e);
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getContext(), "Failed connection", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final CarsResponse carsResponse = new Gson().fromJson(response.body().string(), CarsResponse.class);
//
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        RequestManager imageRequestManager = Glide.with(getContext());
//                        recyclerView.setAdapter(new CarsAdapter(carsResponse.getCars(), imageRequestManager, CarListFragment.this));
//                    }
//                });
//            }
//        });
    }

    @Override
    public void onCarClick(Car car) {
        // TODO: open details screen
    }
}
