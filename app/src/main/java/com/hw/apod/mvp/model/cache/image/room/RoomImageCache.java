package com.hw.apod.mvp.model.cache.image.room;

import com.hw.apod.mvp.model.cache.image.IImageCache;
import com.hw.apod.mvp.model.entity.room.Database;
import com.hw.apod.mvp.model.entity.room.RoomCacheImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RoomImageCache implements IImageCache {

    private Database db;

    private File dir;

    public RoomImageCache(Database db, File dir) {
        this.db = db;
        this.dir = dir;
    }

    @Override
    public Completable saveImage(String url, byte[] byteArray) {
        return Completable.create((emitter) ->{
            if(!dir.exists() && !dir.mkdir()){
                emitter.onError(new IOException("Failed to create cache dir"));
            }
            File imageFile = new File(RoomImageCache.this.getDir(), url);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            outputStream.write(byteArray);
            String path = imageFile.getPath();
            db.imageDao().insert(new RoomCacheImage(url, path));
            emitter.onComplete();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Boolean> contains(String url) {
        return Single.fromCallable(() -> {
            return RoomImageCache.this.getDb().imageDao().findByUrl(url) != null;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Maybe getBytes(String url) {
        return Maybe.fromCallable(() -> {
            RoomCacheImage image = RoomImageCache.this.getDb().imageDao().findByUrl(url);
            FileInputStream inputStream = new FileInputStream(image.localPath);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream(inputStream.read());
            return byteArray;
        }).subscribeOn(Schedulers.io());

    }

    @Override
    public Completable clear() {
        return Completable.fromAction(()->{
            db.imageDao().clear();
            dir.delete();
        }).subscribeOn(Schedulers.io());
    }

    public final Database getDb() {
        return this.db;
    }

    public final File getDir() {
        return this.dir;
    }
}
