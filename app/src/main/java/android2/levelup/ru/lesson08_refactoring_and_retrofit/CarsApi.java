package android2.levelup.ru.lesson08_refactoring_and_retrofit;


import retrofit2.Call;
import retrofit2.http.GET;

public interface CarsApi {

    @GET("1tW53X")
    public Call<CarsResponse> getCars();
}
