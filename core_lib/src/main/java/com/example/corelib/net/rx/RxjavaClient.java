package com.example.corelib.net.rx;

import com.example.corelib.net.HttpMethod;
import com.example.corelib.net.RetorfitBuild;
import com.example.corelib.net.RetorfitCreate;
import com.example.corelib.net.callback.IError;
import com.example.corelib.net.callback.IFailure;
import com.example.corelib.net.callback.ISuccess;
import com.example.corelib.net.callback.RequestCallBack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Rxjava 封装调用类
 * @author 彭翔宇
 */
public class RxjavaClient {
    private String url;
    private Map<String, Object> params;
    private File file;
    private String download_path;
    private RequestBody body;

    public RxjavaClient(String url,Map<String,Object> params,
                        String download_path,File file,RequestBody requestBody) {
        this.url = url;
        this.params = params;
        this.download_path = download_path;
        this.file = file;
        this.body = requestBody;
    }

    public static RxjavaBuild create() {
        return new RxjavaBuild();
    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        return request(HttpMethod.POST);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<String> download() {
        return request(HttpMethod.DOWNLOAD);
    }

    public final Observable<String> get_raw() {
        return request(HttpMethod.GET_RAW);
    }

    public final Observable<String> post_raw() {
        return request(HttpMethod.POST_RAW);
    }

    public final Observable<String> put() {
        return request(HttpMethod.PUT);
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }


    private Observable<String> request(HttpMethod method) {
        final RxjavaService service = RetorfitCreate.getRxjavaService();
        Observable<String> observable = null;
        switch (method) {
            case GET:
                observable = service.get(url, params);
                break;
            case POST:
                observable = service.post(url, params);
                break;
            case UPLOAD:
                observable = service.upload(url, null);
                break;
            case DOWNLOAD:
//                observable =service.download(url,params);
                break;
            case DELETE:
//                observable = service.delete(url, params);
                break;
            case PUT:
                observable = service.put(url, params);
                break;
            case GET_RAW:
                observable = service.getRaw(url, body);
                break;
            case POST_RAW:
                observable = service.postRaw(url, body);
                break;
            default:
                break;
        }
        return observable;
    }
}