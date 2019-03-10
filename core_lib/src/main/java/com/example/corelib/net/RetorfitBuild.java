package com.example.corelib.net;

import com.example.corelib.net.callback.IError;
import com.example.corelib.net.callback.IFailure;
import com.example.corelib.net.callback.ISuccess;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author 彭翔宇
 */
public class RetorfitBuild {

    private String url;
    private Map<String, Object> params;
    private File file;
    private String download_path;
    private ISuccess success;
    private IError error;
    private IFailure failure;
    private RequestBody body;

    public RetorfitBuild() {
        Map<String, Object> params = new HashMap<>();
    }

    public final RetorfitBuild url(String url) {
        this.url = url;
        return this;
    }

    public final RetorfitBuild params(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public final RetorfitBuild file(File file) {
        this.file = file;
        return this;
    }

    public final RetorfitBuild download_path(String download_path) {
        this.download_path = download_path;
        return this;
    }

    public final RetorfitBuild success(ISuccess success) {
        this.success = success;
        return this;
    }

    public final RetorfitBuild error(IError error) {
        this.error = error;
        return this;

    }

    public final RetorfitBuild failure(IFailure failure) {
        this.failure = failure;
        return this;
    }

    public final RetorfitBuild requetbody(RequestBody body) {
        this.body = body;
        return this;
    }

    public final RetorfitClient build() {
        return new RetorfitClient(url,params,download_path,error,failure,success,file,body);
    }

}
