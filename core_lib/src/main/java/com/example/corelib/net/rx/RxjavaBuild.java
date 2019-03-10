package com.example.corelib.net.rx;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author 彭翔宇
 */
public class RxjavaBuild {

    private String url;
    private Map<String, Object> params;
    private File file;
    private String download_path;
    private RequestBody body;

    public RxjavaBuild() {
        Map<String, Object> params = new HashMap<>();
    }

    public final RxjavaBuild url(String url) {
        this.url = url;
        return this;
    }

    public final RxjavaBuild params(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public final RxjavaBuild file(File file) {
        this.file = file;
        return this;
    }

    public final RxjavaBuild download_path(String download_path) {
        this.download_path = download_path;
        return this;
    }


    public final RxjavaBuild requetbody(RequestBody body) {
        this.body = body;
        return this;
    }

    public final RxjavaClient build() {
        return new RxjavaClient(url,params,download_path,file,body);
    }

}
