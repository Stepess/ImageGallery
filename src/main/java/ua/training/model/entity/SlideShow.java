package ua.training.model.entity;

import java.time.LocalDateTime;
import java.util.*;

public class SlideShow extends File{
    public enum SlideShowFormat{
        AVI, DVD, MP4;

        public static boolean contains(String format){
            for (SlideShowFormat slideShowFormat: SlideShowFormat.values())
                if (slideShowFormat.toString().equals(format.toUpperCase()))
                    return true;
            return false;
        }

        public static List<String> getAllFormats(){
            List<String> result = new ArrayList<>();
            for (SlideShowFormat slideShowFormat: SlideShowFormat.values())
                result.add(slideShowFormat.toString().toLowerCase());
            return result;
        }
    }

    private List<Image> frames;
    private Set<String> tags;

    public String getStringOfFrames() {
        StringBuilder sb = new StringBuilder();
        for (Image img: frames)
            sb.append(img.toString()).append("\n");
        return sb.toString();
    }

    public String getStringOfTags() {
        StringBuilder sb = new StringBuilder();
        for (String tag: tags)
            sb.append(tag).append("\n");
        return sb.toString();
    }

    public SlideShow(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit) {
        super(name, format, weightInMb, timeOfLastEdit);
        if (!SlideShowFormat.contains(format))
            throw new IllegalArgumentException("Unfortunately, such format isn't supported.");
        this.frames = new ArrayList<>();
        this.tags = new HashSet<>();
    }

    public SlideShow(String name, String format, double weightInMb, LocalDateTime timeOfLastEdit, List<Image> frames) {
        super(name, format, weightInMb, timeOfLastEdit);
        this.frames = new ArrayList<>();
        this.tags = new HashSet<>();
        for (Image frame: frames){
            this.frames.add(frame);
            this.tags.add(frame.getTag());
            this.weightInMb += frame.getWeightInMb();
        }
    }

    public List<Image> getFrames() {
        return frames;
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

    public void addFrame(Image img, int position) {
        frames.add(position, img);
        tags.add(img.getTag());
        weightInMb += img.getWeightInMb();
        setTimeOfLastEdit(LocalDateTime.now());
    }

    public void deleteFrame(Image frame) {
        weightInMb -= frame.weightInMb;
        removeTagIfThereAreNotImageWithIt(frame);
        frames.remove(frame);
        setTimeOfLastEdit(LocalDateTime.now());

    }

    public void deleteFrame(int position) {
        weightInMb -= frames.get(position).getWeightInMb();
        removeTagIfThereAreNotImageWithIt(frames.get(position));
        frames.remove(position);
        setTimeOfLastEdit(LocalDateTime.now());
    }

    private void removeTagIfThereAreNotImageWithIt(Image img) {
        boolean remove = true;
        for (Image frame: frames)
            if (img != frame && frame.getTag().equals(img.getTag())) {
                remove = false;
                break;
            }
        if (remove)
            tags.remove(img.getTag());
    }

    public void mergeSlideShow(SlideShow slideShow) {
        frames.addAll(slideShow.getFrames());
        tags.addAll(slideShow.getTags());
        weightInMb += slideShow.getWeightInMb();
        setTimeOfLastEdit(LocalDateTime.now());
    }
}
