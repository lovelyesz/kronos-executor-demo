package com.yz.kronos.config;

import com.alibaba.fastjson.JSONObject;
import com.yz.kronos.ExecuteConstant;
import com.yz.kronos.JobInfo;
import com.yz.kronos.exception.JobException;
import com.yz.kronos.execute.JobInfoQueue;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * http实现的job信息队列
 * @author shanchong
 * @date 2019-12-23
 **/
@Component
public class HttpJobInfoQueue implements JobInfoQueue {

    @Value("${kronos.url}")
    private String url;
    /**
     * 获取队列中的一个元素
     *
     * @return
     * @throws JobException
     */
    @Override
    public JobInfo lpop() throws JobException {
        String env = System.getenv(ExecuteConstant.KRONOS_EXECUTOR_ENV_NAME);
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url+"/job/lpop?key="+env).get().build();
        Call call = httpClient.newCall(request);
        String body;
        try {
            body = call.execute().body().string();
        } catch (IOException e) {
            throw new JobException("获取任务信息失败: "+e.getMessage());
        }
        return JSONObject.parseObject(body,JobInfo.class);
    }
}
