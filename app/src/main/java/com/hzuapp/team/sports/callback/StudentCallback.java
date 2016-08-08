package com.hzuapp.team.sports.callback;

import com.hzuapp.team.sports.bean.Student;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by jay on 2016/8/7.
 */
public class StudentCallback extends Callback<Student> {
    @Override
    public Student parseNetworkResponse(Response response) throws Exception {
        System.out.println(response);
        String str = response.body().string();
        System.out.println("在UserCallBack里面的  " + str);

        return null;
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(Student response) {

    }

}
