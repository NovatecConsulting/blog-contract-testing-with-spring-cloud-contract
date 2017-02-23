package product_demo;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.restdocs.WireMockRestDocs;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@AutoConfigureMockMvc
public class ProviderRestDocsDemoTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * The Query parameters are not generated in the restdocs-resource.json
     * To generate them you need to use WireMockRestDocs - see restDocsWireMockDemoTest
     */
    @Test
    public void restDocsDemoTest() throws Exception {
        mockMvc.perform(get("/product?id=537"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{" +
                                "\"name\": \"Consumer Test\"," +
                                "\"description\" : \"Consumer Test verifies provider\"," +
                                "\"type\": \"testing product\"" +
                                "}"))
                .andDo(document("restdocs-resource"));
    }

    @Test
    public void restDocsWireMockDemoTest() throws Exception {
        mockMvc.perform(get("/product?id=537"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("{" +
                                "\"name\": \"Consumer Test\"," +
                                "\"description\" : \"Consumer Test verifies provider\"," +
                                "\"type\": \"testing product\"" +
                                "}"))
                .andDo(WireMockRestDocs.verify().
                        wiremock(WireMock
                                .get(WireMock.urlPathEqualTo("/product"))
                                .withQueryParam("id", equalTo("537")))
                        .stub("restdocs-resource-2"));
    }
}
