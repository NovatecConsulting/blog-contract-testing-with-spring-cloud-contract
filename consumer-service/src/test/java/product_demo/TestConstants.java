package product_demo;

import java.net.URI;

public class TestConstants {
    private static final String URL = "http://localhost:8082";
    public static final URI PRODUCT_INFO_URI = URI.create(String.format("%s/%s", URL, "product?id=537"));

    public static final String EXPECTED_NAME = "Consumer Test";
    public static final String EXPECTED_TYPE = "testing product";
    public static final String EXPECTED_DESC = "Consumer Test verifies provider";
}
