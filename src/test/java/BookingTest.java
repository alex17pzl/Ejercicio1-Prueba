import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BookingTest {

    private static String requestBody = "{\n" +
            "\"firstname\": \"Alejandro\", \n" +
            "\"lastname\": \"Ávila\", \n" +
            "\"totalprice\": 122, \n" +
            "\"depositpaid\": true, \n" +
            "\"bookingdates\": {\n" +
            "\"checkin\": \"2018-01-01\", \n" +
            "\"checkout\": \"2019-01-01\" \n" +
            "}, \n" +
            "\"additionalneeds\": \"Breakfast\" \n" +
            "}";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void testGetBooking() {
        String body = RestAssured.given()
                .when().get("")
                .then()
                    .statusCode(200)
                .extract().body().asString();

        System.out.println(body);
    }

    @Test
    public void testCreateBookingStatusCode() {
        Response response = RestAssured.given()
                .headers("Content-Type", "application/json", "Accept", "application/json")
                .and()
                .body(requestBody)
                .post("/booking");

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void testCreateBookingIdNotNull() {
        RestAssured.given()
                .headers("Content-Type", "application/json", "Accept", "application/json")
                .and()
                .body(requestBody)
                .post("/booking")
                .then()
                .body("bookingid", notNullValue());
    }

    @Test
    public void testCreateExpectedResponse() {
        Response response = RestAssured.given()
                .headers("Content-Type", "application/json", "Accept", "application/json")
                .and()
                .body(requestBody)
                .post("/booking");

        Assertions.assertEquals("Alejandro", response.jsonPath().getString("booking.firstname"));
        Assertions.assertEquals("Ávila", response.jsonPath().getString("booking.lastname"));
        Assertions.assertEquals(122, response.jsonPath().getInt("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getBoolean("booking.depositpaid"));
        Assertions.assertEquals("2018-01-01", response.jsonPath().getString("booking.bookingdates.checkin"));
        Assertions.assertEquals("2019-01-01", response.jsonPath().getString("booking.bookingdates.checkout"));
        Assertions.assertEquals("Breakfast", response.jsonPath().getString("booking.additionalneeds"));
    }

}
