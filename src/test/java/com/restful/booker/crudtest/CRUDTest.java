package com.restful.booker.crudtest;

import com.restful.booker.steps.CreateBookingSteps;
import com.restful.booker.steps.DeleteBookingSteps;
import com.restful.booker.steps.GetBookingSteps;
import com.restful.booker.steps.UpdateBookingSteps;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class CRUDTest extends TestBase {

    static String username = "admin";
    static String password = "password123";
    static String firstname = "Darshna" + TestUtils.getRandomValue();
    static String lastname = "Patel";
    static int totalprice = 999;
    static boolean depositpaid = true;
    static String additionalneeds = "Breakfast";
    static String checkin = "2024-01-06";
    static String checkout = "2024-01-20";
    static int bookingid;
    static String token;

    @Steps
    CreateBookingSteps createBookingSteps;

    @Title("Creating new token")
    @Test
    public void test01() {
        ValidatableResponse response = createBookingSteps.createToken(username, password);
        response.log().all().statusCode(201);
        token = response.extract().path("token");
        System.out.println("token:" + token);
    }

    @Title("This will generate a new booking and also verify new generated booking")
    @Test
    public void test02() {
        ValidatableResponse response = createBookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
        response.log().all().statusCode(200);
        bookingid = response.extract().path("bookingId");
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertEquals(value, hasValue(bookingid));
    }



    @Steps
    GetBookingSteps getBookingSteps;

    @Title("Getting all booking Id")
    @Test
    public void test03(){
        ValidatableResponse response = getBookingSteps.getAllId();
        response.log().all().statusCode(200);
        List<String> booking = response.extract().path("bookingId");
        Assert.assertTrue(booking.contains(bookingid));
    }

    @Title("Getting Single ID")
    @Test
    public void test004(){
        ValidatableResponse response = getBookingSteps.getBookingBySingleId(bookingid);
        response.log().all().statusCode(200);
        HashMap<String,Object> value= response.extract().path("");
        Assert.assertThat(value, hasValue(bookingid));

    }


    @Steps
    UpdateBookingSteps updateBookingSteps;

    @Title("Update booking")
    @Test
    public void test05() {
        firstname = firstname + "-updated";
        ValidatableResponse response = updateBookingSteps.updateBooking(token, bookingid, firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds);
        response = updateBookingSteps.getBookingById(token,bookingid);
        response.log().all().statusCode(200);
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(firstname));
    }

    @Steps
    DeleteBookingSteps deleteBookingSteps;

    @Title("Deleting the booking by ID and verifying deletion.")
    @Test
    public void test06() {
        deleteBookingSteps.deletebooking(token, bookingid).statusCode(201);
        deleteBookingSteps.getBookingById(token, bookingid).statusCode(404);


    }
}
