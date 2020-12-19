package com.hw.apod.mvp.view.image;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hw.apod.mvp.model.cache.image.IImageCache;
import com.hw.apod.mvp.model.network.INetworkStatus;

import java.io.ByteArrayOutputStream;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class GlideImageLoader implements IImageLoader<ImageView> {

    private IImageCache cache;

    private  INetworkStatus status;

    public GlideImageLoader(IImageCache cache, INetworkStatus status) {
        this.cache = cache;
        this.status = status;
    }

    @Override
    public void loadImage(String url, ImageView container) {
        status.isOnlineSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((isOnline)->{
                    if(isOnline){
                        Glide.with(container.getContext())
                                .asBitmap()
                                .load(url)
                                .listener(new RequestListener<Bitmap>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                        return true;
                                    }

                                    @Override
                                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                        resource.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                                        byte[] bytes = outputStream.toByteArray();
                                        cache.saveImage(url, bytes);
                                        return false;
                                    }
                                }).into(container);
                    }else {
                        cache.getBytes(url).observeOn(AndroidSchedulers.mainThread()).subscribe((arr)->{
                            Glide.with(container.getContext()).load(arr).into(container);
                        }, (e)->{

                        });
                    }
                });
    }
}
