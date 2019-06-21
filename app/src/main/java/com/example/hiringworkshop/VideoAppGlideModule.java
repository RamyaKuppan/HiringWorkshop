package com.example.hiringworkshop;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Glide module for banfield app.
 */
@GlideModule
public class VideoAppGlideModule extends AppGlideModule {

    /**
     * Disabling manifest parsing to avoid adding same module twice.
     *
     * @return true or false.
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
