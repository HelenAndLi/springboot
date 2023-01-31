package pers.mrsli.springboot.springbootlearning.utils;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;

public class OkHttpUtil {

    /**
     * GET
     *
     * @param url
     *
     * @return
     *
     * @throws IOException
     */
    public static Response get(String url) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response;
    }

    /**
     * GET，设置header
     *
     * @param url
     * @param headers
     *
     * @return
     *
     * @throws IOException
     */
    public static Response get(String url, HashMap<String, String> headers) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url(url);
        if(headers != null && headers.size() > 0){
            for(String key : headers.keySet()){
                builder.addHeader(key, headers.get(key));
            }
        }
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        return response;
    }

    /**
     * postFormData
     *
     * @param url
     * @param formData
     *
     * @return
     *
     * @throws IOException
     */
    public static Response postFormData(String url, HashMap<String, String> formData) throws IOException{
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        if(formData != null && formData.size() > 0){
            for(String key : formData.keySet()){
                formBodyBuilder.add(key, formData.get(key));
            }
        }
        RequestBody requestBody = formBodyBuilder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();
        return response;
    }

    /**
     * postFormData，设置header
     *
     * @param url
     * @param formData
     * @param headers
     *
     * @return
     *
     * @throws IOException
     */
    public static Response postFormData(String url, HashMap<String, String> formData,
                                        HashMap<String, String> headers) throws IOException{
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        if(formData != null && formData.size() > 0){
            for(String key : formData.keySet()){
                formBodyBuilder.add(key, formData.get(key));
            }
        }
        RequestBody requestBody = formBodyBuilder.build();
        Request.Builder builder = new Request.Builder().url(url).post(requestBody);
        if(headers != null && headers.size() > 0){
            for(String key : headers.keySet()){
                builder.addHeader(key, headers.get(key));
            }
        }
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        return response;
    }

    /**
     * postJsonData
     *
     * @param url
     * @param jsonData
     *
     * @return
     *
     * @throws IOException
     */
    public static Response postJsonData(String url, String jsonData) throws IOException{
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonData);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();
        return response;
    }

    /**
     * postJsonData，设置header
     *
     * @param url
     * @param jsonData
     * @param headers
     *
     * @return
     *
     * @throws IOException
     */
    public static Response postJsonData(String url, String jsonData, HashMap<String, String> headers) throws IOException{
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonData);
        Request.Builder builder = new Request.Builder().url(url).post(requestBody);
        if(headers != null && headers.size() > 0){
            for(String key : headers.keySet()){
                builder.addHeader(key, headers.get(key));
            }
        }
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        return response;
    }


}
