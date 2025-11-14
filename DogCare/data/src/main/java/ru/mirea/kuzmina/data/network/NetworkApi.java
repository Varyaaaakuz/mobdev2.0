package ru.mirea.kuzmina.data.network;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
public interface NetworkApi {

    @GET("api/breeds/image/random/10")
    Call<DogApiResponse> getRandomDogImages();
    class Creator {
        public static NetworkApi create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://dog.ceo/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(NetworkApi.class);
        }
    }
}
class MockResponse {
    public String message;
    public java.util.List<MockItem> data;
}
class MockItem {
    public int id;
    public String name;
    public String description;
}