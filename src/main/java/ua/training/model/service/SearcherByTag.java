package ua.training.model.service;

import ua.training.model.entity.Image;

import java.util.*;

public class SearcherByTag implements Service<Image> {
    private Set<String> tags;

    public SearcherByTag(String... tags) {
        if (tags.length == 0)
            throw new IllegalArgumentException("There are no tags!");
        this.tags = new HashSet<String>();
        Collections.addAll(this.tags, tags);
    }

    @Override
    public List<Image> search(List<Image> data) {
        List<Image> result = new ArrayList<>();

        for (Image img: data)
            if ( tags.contains(img.getTag()) )
                result.add(img);

        return result;
    }
}