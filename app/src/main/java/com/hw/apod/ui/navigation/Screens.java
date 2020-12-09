package com.hw.apod.ui.navigation;

import androidx.fragment.app.Fragment;

import com.hw.apod.mvp.model.entity.AstronomyLore;
import com.hw.apod.ui.fragment.APODDetailFragment;
import com.hw.apod.ui.fragment.APODSearchFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static class APODSearchScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return APODSearchFragment.getInstance();
        }
    }

    public static class APODDetailScreen extends SupportAppScreen {
        private final AstronomyLore astronomyLore;

        public APODDetailScreen(AstronomyLore astronomyLore) {
            this.astronomyLore = astronomyLore;
        }

        @Override
        public Fragment getFragment() {
            return APODDetailFragment.newInstance(astronomyLore);
        }
    }


}
