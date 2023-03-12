package dev.srebootcamp.images.controllers;


import dev.srebootcamp.images.domain.Image;
import dev.srebootcamp.images.services.IImageService;
import dev.srebootcamp.images.utils.ListHelpers;
import dev.srebootcamp.images.utils.StringHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {

    @Autowired
    public IImageService imageService;

    @GetMapping(value = "/images/{compound}", produces = "application/json")
    public List<Image> allImages(@PathVariable String compound) {
        return imageService.getAllImages(compound);
    }

    @GetMapping(value = "/images/{compound}/{ids}", produces = "application/json")
    public List<Image> images(@PathVariable String compound, @PathVariable String ids) {
        List<String> theIds = StringHelpers.split(ids, ",");
        List<Image> images = imageService.getImages(compound, theIds);
        ListHelpers.forEachWithIndex(images, (image, i) -> System.out.println(i + " " + image));
        return images;
    }
}