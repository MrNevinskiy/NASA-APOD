package com.hw.apod.mvp.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AstronomyLoreRepo {
    private List<AstronomyLore> astronomyLore = new ArrayList<>(Arrays.asList(
            new AstronomyLore("2002-02-20", "Explanation", "HD url", "Title", "url")
    ));
    public List<AstronomyLore> getAstronomyLore(){
        return Collections.unmodifiableList(astronomyLore);
    }
}
