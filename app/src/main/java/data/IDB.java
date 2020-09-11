package data;

import java.util.List;
import io.reactivex.Observable;
import pojo.Model;
import retrofit2.http.GET;

public interface IDB {

    @GET("posts")
    public Observable<List<Model>> getPost();
}
