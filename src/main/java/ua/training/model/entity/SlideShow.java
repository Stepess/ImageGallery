package ua.training.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlideShow extends File{
    public enum SlideShowFormat{
        AVI, DVD, MP4
    }

    private List<Image> frames;
    private Set<String> tags;

    public SlideShow(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit) {
        super(name, format, weightInMb, timeOfLastEdit);
        frames = new ArrayList<>();
        tags = new HashSet<>();
    }

    public SlideShow(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, List<Image> frames) {
        super(name, format, weightInMb, timeOfLastEdit);
        this.frames = frames;
        for (Image frame: frames){
            tags.add(frame.getTag());
            weightInMb += frame.getWeightInMb();
        }

    }

    public List<Image> getFrames() {
        return frames;
    }

    public void setFrames(List<Image> frames) {
        this.frames = frames;
        setTimeOfLastEdit(LocalDateTime.now());
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public void open() {
        for (Image frame: frames)
            System.out.println(frame);
    }

    @Override
    public void edit() {
        System.out.println("Some changes");
        setTimeOfLastEdit(LocalDateTime.now());
    }

    public void addFrame(Image img) {
        frames.add(img);
        tags.add(img.getTag());
        weightInMb += img.getWeightInMb();
        setTimeOfLastEdit(LocalDateTime.now());
    }

    public void setFrame(Image img, int position) {
        frames.add(position, img);
        tags.add(img.getTag());
        weightInMb += img.getWeightInMb();
        setTimeOfLastEdit(LocalDateTime.now());
    }

    public void deleteFrame(int position) {
        weightInMb -= frames.get(position).getWeightInMb();
        removeTagIfThereAreNotImageWithIt(frames.get(position).getTag());
        frames.remove(position);
        setTimeOfLastEdit(LocalDateTime.now());
    }

    private void removeTagIfThereAreNotImageWithIt(String tag) {
        boolean remove = true;
        for (Image frame: frames)
            if (frame.getTag().equals(tag)) {
                remove = false;
                break;
            }
        if (remove)
            tags.remove(tag);
    }

    public void mergeSlideShow(SlideShow slideShow) {
        frames.addAll(slideShow.getFrames());
        tags.addAll(slideShow.getTags());
        weightInMb += slideShow.getWeightInMb();
        setTimeOfLastEdit(LocalDateTime.now());
    }
}
