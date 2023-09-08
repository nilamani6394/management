package io.nilmani.management.controller;

import io.nilmani.management.entity.UserEntity;
import io.nilmani.management.entity.VideoCategory;
import io.nilmani.management.repository.UserEntityRepo;
import io.nilmani.management.repository.VideoCategoryRepository;
import io.nilmani.management.service.VideoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/video")
public class videoController {
    @Autowired
    private VideoStorageService videoStorageService;
    @Autowired
    private VideoCategoryRepository videoCategoryRepo;
    @Autowired
    private UserEntityRepo userRepo;

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        // Load necessary data for the form (e.g., user list, video categories)
        List<UserEntity> users = userRepo.findAll();
       // List<VideoCategory> categories = videoCategoryRepo.findAll();
        List<VideoCategory> categories = videoCategoryRepo.findAll();

        model.addAttribute("users", users);
        model.addAttribute("categories", categories);

        return "upload"; // Return the name of your Thymeleaf HTML template
    }

    @PostMapping("/upload")
    public String uploadVideo(@RequestParam("file") MultipartFile file,
                              @RequestParam("id") String id,
                              @RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("categoryId") Long categoryId,
                              Model model) {
        try {
            // Load user and category entities from the database
           // UserEntity userEntity = userRepo.findById(userId).orElse(null);
            UserEntity userEntity = userRepo.findByUserId(id);//findByUserId(String.valueOf(userId));
            VideoCategory videoCategory = videoCategoryRepo.findById(categoryId).orElse(null);
//            VideoCategory videoCategory = (VideoCategory) videoCategoryRepo.findAllById(Collections.singleton(categoryId));

            if (userEntity == null || videoCategory == null) {
                return "error"; // Handle error gracefully
            }

            // Store the uploaded video and get the unique filename
            String uniqueFileName = videoStorageService.storeVideo(file, userEntity, title, description, videoCategory);

            model.addAttribute("message", "Video uploaded successfully. File name: " + uniqueFileName);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload the video.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
        }

        return "upload"; // Return the name of your Thymeleaf HTML template
    }

//
//@GetMapping("/upload")
//public String showUploadForm() {
//    return "upload"; // Return the name of your Thymeleaf HTML template
//}

   /* @PostMapping("/upload")
    public String uploadVideo(@RequestParam("file") MultipartFile file, Model model) {
        try {
            // Store the uploaded video and get the unique filename
            String uniqueFileName = videoStorageService.storeVideo(file);

            model.addAttribute("message", "Video uploaded successfully. File name: " + uniqueFileName);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload the video.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
        }

        return "upload"; // Return the name of your Thymeleaf HTML template
    }*/
}
