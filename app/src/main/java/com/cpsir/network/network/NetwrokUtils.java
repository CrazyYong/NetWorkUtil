package com.cpsir.network.network;

import com.cpsir.network.model.AliGeoModel;
import com.cpsir.network.model.Constants;
import com.cpsir.network.utils.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by czy on 2017/7/17.
 * 网络公共类
 */

public class NetwrokUtils {

    private NetwrokUtils() {

    }

    // 定义一个SingletonTest类型的变量（不初始化，注意这里没有使用final关键字）
    private static NetwrokUtils instance;

    // 定义一个静态的方法（调用时再初始化SingletonTest，但是多线程访问时，可能造成重复初始化问题）
    public static NetwrokUtils getInstance() {
        if (instance == null)
            instance = new NetwrokUtils();
        return instance;
    }


    /**
     * 获取阿里云地理位置
     * @param a
     * @param aa
     * @param aaa
     */
    public  void getGeo(String a,String aa,String aaa){
        Call<AliGeoModel> call = RetrofitUtils.getInstance().getAliGeo(a,aa,aaa);
        call.enqueue(new Callback<AliGeoModel>() {
            @Override
            public void onResponse(Call<AliGeoModel> call, Response<AliGeoModel> response) {
                LogUtil.i(Constants.TAG,"onResponse:\n"+response.body().toString());
            }

            @Override
            public void onFailure(Call<AliGeoModel> call, Throwable t) {
                LogUtil.i(Constants.TAG,"onFailure:"+t.getMessage());
            }
        });
    }




//    /**
//     * 上传图片
//     * @param body
//     */
//    public void uploadImgFile(MultipartBody.Part body){
//        Call<BaseModel> call = RetrofitUtils.getInstance().uploadImgFile(body);
//        call.enqueue(new Callback<BaseModel>() {
//            @Override
//            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
//                LogUtil.i(Constants.TAG,"上传图片:"+response.body().toString());
//                sendToast(response.body().getMsg());
//                if(response.body().getCode().equals("1010")){
//                        uploadStudentImgFile(response.body().getImgurl());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BaseModel> call, Throwable t) {
//                sendToast("请求失败，请检查网络");
//            }
//        });
//    }



}
