package io.nilmani.management.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
public class Video {
    @Id
    @GeneratedValue(generator = "video_id")
    @GenericGenerator(name = "video_id",strategy="io.nilmani.management.customId.CustomVideoID")
    private  Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private  UserEntity userEntity;
    private String title;
    private String description;
    private String videoFileName;
    private Date uploadDate;
    @ManyToOne
    @JoinColumn(name ="category_id")
    private VideoCategory videoCategory;

    public Video(){}

    public Video(Long id, UserEntity userEntity,
                 String title, String description, String videoFileName, Date uploadDate, VideoCategory videoCategory) {
        this.id = id;
        this.userEntity = userEntity;
        this.title = title;
        this.description = description;
        this.videoFileName = videoFileName;
        this.uploadDate = uploadDate;
        this.videoCategory = videoCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public VideoCategory getVideoCategory() {
        return videoCategory;
    }

    public void setVideoCategory(VideoCategory videoCategory) {
        this.videoCategory = videoCategory;
    }
}
