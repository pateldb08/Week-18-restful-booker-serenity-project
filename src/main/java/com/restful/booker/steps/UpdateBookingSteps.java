package com.restful.booker.steps;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.UpdateBookingPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UpdateBookingSteps {
    static String token;

    @Step("Update Booking using token {0}, bookingId {1}, firstname {2}, lastname {3}, totalprice {4}, depositpaid {5}, checkindate {6}, checkoutdate {7}, additionalneeds {8}")
    public ValidatableResponse updateBooking(String token, int bookingId, String firstname, String lastname, int totalprice, Boolean depositpaid, String checkindate, String checkoutdate, String additionalneeds) {
        UpdateBookingPojo.BookingDates dates = new UpdateBookingPojo.BookingDates();  // BookingDates class is subclass of UpdateBookingPojo so created object like this
        dates.setCheckin(checkindate);
        dates.setCheckout(checkoutdate);

        UpdateBookingPojo updateBookingPojo = new UpdateBookingPojo();
        updateBookingPojo.setFirstname(firstname);
        updateBookingPojo.setLastname(lastname);
        updateBookingPojo.setTotalprice(totalprice);
        updateBookingPojo.setDepositpaid(depositpaid);
        updateBookingPojo.setBookingdates(dates);
        updateBookingPojo.setAdditionalneeds(additionalneeds);
        updateBookingPojo.setBookingid(bookingId);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingId", bookingId)
                .body(updateBookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING)
                .then().log().all();
    }
        @Step("Getting student information with bookingId: {0}")
                public ValidatableResponse getBookingById(String token, int bookingid) {
        return SerenityRest.given().log().all()
                .header("Cookie", "token=" + token)
                .pathParam("bookingId", bookingid)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();

        }

    }



