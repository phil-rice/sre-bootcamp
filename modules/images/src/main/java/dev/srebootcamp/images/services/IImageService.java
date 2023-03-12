package dev.srebootcamp.images.services;

import dev.srebootcamp.images.domain.Image;

import java.util.List;
import java.util.Optional;

public interface IImageService {
    List<Image> getAllImages(String compoundId);

    List<Image> getImages(String compoundId, List<String> ids);

    Optional<Image> getImage(String compoundId, String id);
}
