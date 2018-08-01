package ua.training.model.service.database;

import ua.training.model.entity.SlideShow;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SlideShows {
    private static SlideShows ourInstance = new SlideShows();
    private static ConcurrentMap<String, SlideShow> slideShows = new ConcurrentHashMap<>();

    public static SlideShows getInstance() {
        return ourInstance;
    }

    private SlideShows() {

    }

    public void add(SlideShow slideShow) {
        slideShows.put(slideShow.getName(), slideShow);
    }

    public boolean isNameUnique(String name) {
        for (String str : slideShows.keySet())
            if (str.equals(name))
                return false;

        return true;
    }

    public ConcurrentMap<String, SlideShow> getSlideShows() {
        return slideShows;
    }
}
