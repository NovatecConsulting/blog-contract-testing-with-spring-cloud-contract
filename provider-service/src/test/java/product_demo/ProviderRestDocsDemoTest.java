package product_demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureMockMvc
public class ProviderRestDocsDemoTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void restDocsDemoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product")
                .param("id", "537"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json("{" +
                                "\"name\": \"Consumer Test\"," +
                                "\"description\" : \"Consumer Test verifies provider\"," +
                                "\"type\": \"testing product\"" +
                                "}"))
                .andDo(document("restdocs-resource"));
    }
}
