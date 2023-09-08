package io.nilmani.management.service;

import io.nilmani.management.configuration.StorageConfig;
import io.nilmani.management.entity.UserEntity;
import io.nilmani.management.entity.Video;
import io.nilmani.management.entity.VideoCategory;
import io.nilmani.management.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;


@Service
public class LocalVideoStoreService  implements VideoStorageService{
   @Autowired
    private StorageConfig storeConfig;
   @Autowired
   private VideoRepository videoRepository;

    @Override
    public String storeVideo(MultipartFile file, UserEntity userEntity, String title, String description, VideoCategory videoCategory) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty.");
        }

        String uploadDirectory = storeConfig.getVideoUploaderLocation();

        // Create the storage directory if it doesn't exist
        File storageDirectory = new File(uploadDirectory);
        if (!storageDirectory.exists()) {
            storageDirectory.mkdirs();
        }

        // Generate a unique file name (e.g., using a UUID)
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "-" + originalFileName;

        // Save the video file to the storage location
        Path filePath = Paths.get(uploadDirectory, uniqueFileName);
        file.transferTo(filePath);

        // Create a new Video entity and set its properties
        Video video = new Video();
        video.setUserEntity(userEntity);
        video.setTitle(title);
        video.setDescription(description);
        video.setVideoFileName(uniqueFileName);
        video.setUploadDate(new Date());
        video.setVideoCategory(videoCategory);

        // Save the Video entity to the database
        //videoRepository.save(video);
       videoRepository.save(video);

        return uniqueFileName;
    }
   /* @Override
    public String storeVideo(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()){
            throw new IllegalArgumentException("Upload File is Empty");
        }
        String uploadDirectory = storeConfig.getVideoUploaderLocation();
        //Create Storage Directory if it doesn't exist
        File storageDirectory = new File(uploadDirectory);
        if(!storageDirectory.exists()){
            storageDirectory.mkdirs();
        }
        //Generate unique file name
        String originalFileName = multipartFile.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "-" + originalFileName;

        // Save the video file to the storage location
        Path filePath = Paths.get(uploadDirectory, uniqueFileName);
        multipartFile.transferTo(filePath);
        return uniqueFileName;
    }*/

}
