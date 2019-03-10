package com.example.corelib.net;

import com.example.corelib.net.callback.IError;
import com.example.corelib.net.callback.IFailure;
import com.example.corelib.net.callback.ISuccess;
import com.example.corelib.net.callback.RequestCallBack;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author 彭翔宇
 */
public class RetorfitClient {
    private String url;
    private Map<String, Object> params;
    private File file;
    private String download_path;
    private ISuccess success;
    private IError error;
    private IFailure failure;
    private RequestBody body;

    public RetorfitClient(String url,Map<String,Object> params,
                          String download_path,IError error,IFailure failure,
                          ISuccess success,File file,RequestBody requestBody) {
        this.url = url;
        this.params = params;
        this.download_path = download_path;
        this.error = error;
        this.failure = failure;
        this.success = success;
        this.file = file;
        this.body = requestBody;
    }

    public static RetorfitBuild create() {
        return new RetorfitBuild();
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        request(HttpMethod.DOWNLOAD);
    }

    public final void get_raw() {
        request(HttpMethod.GET_RAW);
    }

    public final void post_raw() {
        request(HttpMethod.POST_RAW);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    private Callback<String> getReqeustCallback() {
        return new RequestCallBack(success, failure, error);
    }


    private void request(HttpMethod method) {
        final RetorfitService service = RetorfitCreate.getRetorfitService();
        Call call = null;
        switch (method) {
            case GET:
                call = service.get(url, params);
                break;
            case POST:
                call = service.post(url, params);
                break;
            case UPLOAD:
//                call =service.upload(url,file);
                break;
            case DOWNLOAD:
                call =service.download(url,params);
                break;
            case DELETE:
                call = service.delete(url, params);
                break;
            case PUT:
                call = service.put(url, params);
                break;
            case GET_RAW:
                call =service.getRaw(url,body);
                break;
            case POST_RAW:
                call =service.postRaw(url,body);
                break;
            default:
                break;
        }
        call.enqueue(getReqeustCallback());
    }
}