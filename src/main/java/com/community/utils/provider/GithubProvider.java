package com.community.utils.provider;

import com.alibaba.fastjson.JSON;
import com.community.entity.dto.AccessTokenDTO;
import com.community.entity.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {


    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        String url = "https://github.com/login/oauth/access_token";
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String s = response.body().string();
            System.out.println(s);
            String token = s.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser gitHubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.github.com/user?access_token="+accessToken;
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubUser gitHubUser =JSON.parseObject(string,GitHubUser.class);
            return gitHubUser;
        }catch (IOException e){

        }
        return null;
    }
}
