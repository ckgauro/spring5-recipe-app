package com.gauro.spring5recipe.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Chandra
 */
public interface ImageService {
    void saveImageFile(Long recipeId, MultipartFile file);
}
