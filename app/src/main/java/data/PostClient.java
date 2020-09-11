package data;

import java.util.List;

import io.reactivex.Observable;
import pojo.Model;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static PostClient INSTANCE;
    private IDB idb;

    public PostClient () {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // This line to convert the call to rx java
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        idb = retrofit.create(IDB.class);
    }

    public static PostClient getINSTANCE () {
        if (INSTANCE == null) {
            INSTANCE = new PostClient();
        }
        return INSTANCE;
    }

    public Observable<List<Model>> getPosts () {
        return idb.getPost();
    }
}

