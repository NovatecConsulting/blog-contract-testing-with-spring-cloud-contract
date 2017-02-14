package product_demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static product_demo.TestConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureStubRunner(ids = "info.novatec.spring.contract.cloud.example:provider-service:+:stubs:8082", workOffline = true)
@DirtiesContext
public class ConsumerDemoTest {

    @Test
    public void consumerDemoContractTest() {

        ProductRestFetcher productRestFetcher = new ProductRestFetcher();
        Product product = productRestFetcher.fetchProductInfo(PRODUCT_INFO_URI);

        assertEquals(EXPECTED_DESC, product.getDescription());
        assertEquals(EXPECTED_NAME, product.getName());
        assertEquals(EXPECTED_TYPE, product.getType());
    }
}
