package io.nilmani.management.service;

import io.nilmani.management.entity.UserEntity;
import io.nilmani.management.entity.VideoCategory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoStorageService {
    String storeVideo(MultipartFile file, UserEntity userEntity, String title, String description, VideoCategory videoCategory) throws IOException;
}
