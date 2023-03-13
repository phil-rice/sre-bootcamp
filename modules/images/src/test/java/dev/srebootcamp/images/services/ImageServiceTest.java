package dev.srebootcamp.images.services;

import dev.srebootcamp.images.ImagesFixture;
import dev.srebootcamp.images.domain.Image;
import dev.srebootcamp.images.utils.ListHelpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.util.List;
import java.util.Optional;

import static dev.srebootcamp.images.ImagesFixture.*;
import static dev.srebootcamp.images.utils.ListHelpers.combine;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ImageServiceTest {

    static JdbcTemplate template = new JdbcTemplate(ds);

    @BeforeAll
    public static void setup() {
        putImagesForC1andC2IntoTheDatabase(template);
    }


    public ImageService imageService() {
        JdbcTemplate template = new JdbcTemplate(ds);
        ImageService imageService = new ImageService();
        imageService.template = template;
        return imageService;
    }

    @Test
    public void testGetAllImage() {
        assertEquals(ImagesFixture.imagesc1, imageService().getAllImages("c1"));
        assertEquals(ImagesFixture.imagesc2, imageService().getAllImages("c2"));
    }

    @Test
    public void testGetImages() throws Exception {
        assertEquals(List.of(im1, im3), imageService().getImages("c1", List.of("id1", "id3")));
        assertEquals(ListHelpers.map(List.of(im1, im3), i -> addCompoundId("c2").apply(i)), imageService().getImages("c2", List.of("id1", "id3")));
    }

    @Test
    public void testGetImagesWhenIdNotFoundNeedToTalkThisOver() { //TODO Need to talk this over with PO
        assertEquals(List.of(im1, im3), imageService().getImages("c1", List.of("id1", "idNotIn", "id3")));
    }

    @Test
    public void testGetImage() {
        assertEquals(Optional.of(im1), imageService().getImage("c1", "id1"));
        assertEquals(Optional.of(im1c2), imageService().getImage("c2", "id1"));
        assertEquals(Optional.empty(), imageService().getImage("c1", "idNotIn"));
    }

}