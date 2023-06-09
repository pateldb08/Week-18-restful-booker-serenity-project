package com.restful.booker.steps;

import com.restful.booker.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class DeleteBookingSteps {

    @Step("Deleting booking informaion with bookingId : {1}")
    public ValidatableResponse deletebooking(String token, int bookingId) {
        return SerenityRest.given().log().all()
                .header("Cookie", "token=" + token)
                .pathParam("bookingId", bookingId)
                .when()
                .delete(EndPoints.DELETE_BY_ID)
                .then();
    }

    @Step("Getting blooking information with bookingId :{1}")
    public ValidatableResponse getBookingById(String token,int bookingId) {
        return SerenityRest.given().log().all()
                .header("Cookie", "token=" + token)
                .pathParam("bookingId", bookingId)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }


}
