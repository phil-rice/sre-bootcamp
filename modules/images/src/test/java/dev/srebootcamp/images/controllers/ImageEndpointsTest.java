package dev.srebootcamp.images.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static dev.srebootcamp.images.ImagesFixture.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ImageEndpointsTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ImageController controller;

    @Test
    public void testAllImages() throws Exception {
        controller.imageService = testImageService;
        mockMvc.perform(get("/images/c1").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(imagec1Json()));
    }

    @Test
    public void testImages() throws Exception {
        controller.imageService = testImageService;
        mockMvc.perform(get("/images/c1/id1,id3").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(imagec1im13Json()));
    }
}