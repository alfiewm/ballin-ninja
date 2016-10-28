package meng.recyclerviewtest.data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by meng on 2016/10/20.
 */

public interface ImageApi {
    @GET("api/data/福利/{pageCount}/{pageIndex}")
    Call<BaseModel<ArrayList<ImageData>>> defaultBenefits(@Path("pageCount") int pageCount, @Path("pageIndex") int pageIndex);
}
