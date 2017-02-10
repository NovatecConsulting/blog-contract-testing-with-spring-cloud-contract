package product_demo;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ContractVerifierBaseTest {

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(new ProductController());
    }
}
