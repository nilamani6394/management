package io.nilmani.management.repository;

import io.nilmani.management.entity.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCategoryRepository  extends JpaRepository<VideoCategory,Long> {
}
