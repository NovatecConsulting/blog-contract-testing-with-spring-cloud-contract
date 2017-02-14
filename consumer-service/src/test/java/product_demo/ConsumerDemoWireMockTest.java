package product_demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static junit.framework.TestCase.assertEquals;
import static product_demo.TestConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Application.class)
@AutoConfigureWireMock(port = 8082)
public class ConsumerDemoWireMockTest {
    @Autowired
    private ProductRestFetcher productRestFetcher;

    private Product product;

    @Test
    public void consumerDemoWireMockContractTest() throws Exception {

        stubFor(get(urlPathEqualTo("/product")).withQueryParam("id", equalTo("537"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json;charset=UTF-8")
                        .withBody("{" +
                                "\"name\": \"Consumer Test\"," +
                                "\"description\" : \"Consumer Test verifies provider\"," +
                                "\"type\": \"testing product\"" +
                                "}")));

        product = productRestFetcher.fetchProductInfo(PRODUCT_INFO_URI);

        assertEquals(EXPECTED_DESC, product.getDescription());
        assertEquals(EXPECTED_NAME, product.getName());
        assertEquals(EXPECTED_TYPE, product.getType());
    }
}
