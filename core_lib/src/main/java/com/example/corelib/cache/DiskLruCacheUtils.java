package com.example.corelib.cache;

import android.content.Context;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.os.Environment.isExternalStorageRemovable;

public class DiskLruCacheUtils {
    private static DiskLruCacheUtils instance;
    private DiskLruCache cache;

    private DiskLruCacheUtils(Context context) {
        try {
            cache = DiskLruCache.open(getSaveFile(context), 1, 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DiskLruCacheUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (DiskLruCacheUtils.class) {
                if (instance == null) {
                    instance = new DiskLruCacheUtils(context);
                }
            }
        }
        return instance;
    }

    public void put(String data) {
        DiskLruCache.Editor edit = null;
        try {
            edit = cache.edit("data");
            OutputStream outputStream = edit.newOutputStream(0);
            outputStream.write(data.getBytes("utf-8"));
            outputStream.flush();
            edit.commit();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                edit.abort();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public String get() {
        String data = null;
        try {
            DiskLruCache.Snapshot snapshot = cache.get("data");
            if(snapshot !=null) {
                InputStream inputStream = snapshot.getInputStream(0);
                byte[] buff = new byte[inputStream.available()];
                inputStream.read(buff);
                inputStream.close();
                data = new String(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  data;
    }

    private File getSaveFile(Context context) {
        final String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !isExternalStorageRemovable()
                ? context.getExternalCacheDir().getPath()
                : context.getCacheDir().getPath();
        return new File(cachePath + File.separator + "hosity");
    }
}
