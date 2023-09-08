package io.nilmani.management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
public class VideoCategory {
    @Id
    @GeneratedValue(generator = "videoCat_id")
    @GenericGenerator(name ="videoCat_id",strategy = "io.nilmani.management.customId.VideoCustomId")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "videoCategory")
    private List<Video> videos;
    public VideoCategory(){}

    public VideoCategory(Long id, String name, List<Video> videos) {
        this.id = id;
        this.name = name;
        this.videos = videos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
