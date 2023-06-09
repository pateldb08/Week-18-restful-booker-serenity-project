package com.restful.booker.steps;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.AuthorisationPojo;
import com.restful.booker.model.BookingPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CreateBookingSteps {
    @Step("Create Token")
    public ValidatableResponse createToken(String username, String password) {
        AuthorisationPojo authorisationPojo = new AuthorisationPojo();
        authorisationPojo.setUsername(username);  // setting username using authorisationpojo class object
        authorisationPojo.setPassword(password);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(authorisationPojo)
                .post(EndPoints.GET_AUTH)
                .then();
    }

    @Step("Create new Booking with firstname{0},lastname{1}, totalprice{2}, depositpaid {3}, checkindate {4}, checkoutdate {5}, additionalneeds {6}")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, Boolean depositpaid, String checkindate, String checkoutdate, String additionalneeds) {
        BookingPojo.BookingDates dates = new BookingPojo.BookingDates();
        dates.setCheckin(checkindate);
        dates.setCheckout(checkoutdate);

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(dates);
        bookingPojo.setAdditionalneeds(additionalneeds);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept","application/json")
                .body(bookingPojo)
                .when()
                .post(EndPoints.GET_BOOKING)
                .then().log().all();

    }


}


