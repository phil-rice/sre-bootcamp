package dev.srebootcamp.images;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.srebootcamp.images.domain.Image;
import dev.srebootcamp.images.services.IImageService;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ImagesFixture {
    static DataSource ds = DataSourceBuilder.create()
            .driverClassName("org.h2.Driver")
            .url("jdbc:h2:mem:testdb")
            .username("sa")
            .password("")
            .build();

    Image im1 = new Image("c1", "url1", "id1");
    Image im1c2 = new Image("c2", "url1", "id1");
    Image im2 = new Image("c1", "url2", "id2");
    Image im3 = new Image("c1", "url3", "id3");

    List<Image> imagesc1 = List.of(im1, im2, im3);
    List<Image> imagesc2 = imagesc1.stream().map(addCompoundId("c2")).toList();

    static String imagec1Json() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(imagesc1);
    }

    static String imagec1im13Json() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(List.of(im1, im3));
    }

    static Function<Image, Image> addCompoundId(String compoundId) {
        return image -> new Image(compoundId, image.url(), image.id());
    }

    IImageService testImageService = new IImageService() {


        @Override
        public List<Image> getAllImages(String compoundId) {
            return imagesc1.stream().map(addCompoundId(compoundId)).toList();
        }

        @Override
        public List<Image> getImages(String compoundId, List<String> ids) {
            return imagesc1.stream().filter(image -> ids.contains(image.id())).map(addCompoundId(compoundId)).toList();
        }

        @Override
        public Optional<Image> getImage(String compoundId, String id) {
            return getImages(compoundId, List.of(id)).stream().findFirst().map(addCompoundId(compoundId));
        }
    };
}
