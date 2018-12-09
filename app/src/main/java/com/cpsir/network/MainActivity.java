package com.cpsir.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cpsir.network.model.AliGeoModel;
import com.cpsir.network.model.Constants;
import com.cpsir.network.network.NetwrokUtils;
import com.cpsir.network.network.RetrofitUtils;
import com.cpsir.network.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btn_get_geo;
    private int requesSuccess=0;
    private int requestFailure=0;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_get_geo=(Button)findViewById(R.id.btn_get_geo);
        list.add("图片1");
        list.add("图片2");
        list.add("图片3");

        btn_get_geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size()==0){
                    Toast.makeText(MainActivity.this,"已经请求完",Toast.LENGTH_SHORT).show();
                    return;
                }
               getGeo("图片1");
            }
        });
    }


    public  void getGeo(final String imgPath){
        LogUtil.i(Constants.TAG,"这是第几张:"+imgPath);
        Call<AliGeoModel> call = RetrofitUtils.getInstance().getAliGeo("上海市","松江区","车墩镇");
        call.enqueue(new Callback<AliGeoModel>() {
            @Override
            public void onResponse(Call<AliGeoModel> call, Response<AliGeoModel> response) {
                LogUtil.i(Constants.TAG,"onResponse:\n");
                if (response.code()==200){
                    requesSuccess++;
                    list.remove(0);
                    if(list.size()==0){
                        return;
                    }else{
                        getGeo(list.get(0));
                    }

                }
            }

            @Override
            public void onFailure(Call<AliGeoModel> call, Throwable t) {
                requestFailure++;
                LogUtil.i(Constants.TAG,"第几次上传失败:"+list.indexOf(imgPath));

            }
        });
    }
}
