package ${group}.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_${packageDetails.name}_endpoint() throws Exception {
        mockMvc.perform(get("${packageDetails.sre.endpoint}"))
                .andExpect(status().isOk());
    }
}