package io.nilmani.management.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Value("${upload.video.location}")
    private String videoUploaderLocation;
    public String getVideoUploaderLocation(){
        return videoUploaderLocation;
    }
}
