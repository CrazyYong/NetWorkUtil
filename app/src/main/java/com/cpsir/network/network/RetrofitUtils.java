package com.cpsir.network.network;

import com.cpsir.network.model.AliGeoModel;
import com.cpsir.network.model.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RetrofitUtils {

    private static Retrofit retrofit = null;
    private static IRetrofitServer iServer;

    public static IRetrofitServer getInstance() {
        if (retrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.aliBaseUrl)
                            .addConverterFactory(GsonConverterFactory.create())//Gson转换器
                            .build();
                    iServer = retrofit.create(IRetrofitServer.class);
                }
            }
        }
        return iServer;
    }

    public interface IRetrofitServer {


//        /**
//         * 上传图片
//         * @return
//         */
//        @Multipart
//        @POST(Constants.uploadImgFileUrl)
//        Call<BaseModel> uploadImgFile(@Part MultipartBody.Part file);


        /**
         *
         * @param a 市
         * @param aa 区
         * @param aaa 镇
         * @return
         */
        @POST(Constants.aliGeoGetUrl)
        Call<AliGeoModel> getAliGeo(@Query("a") String a,@Query("aa") String aa,@Query("aaaa") String aaa);





    }
}
