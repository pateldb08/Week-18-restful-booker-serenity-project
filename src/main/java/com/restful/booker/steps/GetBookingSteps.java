package com.restful.booker.steps;

import com.restful.booker.constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GetBookingSteps {

    @Step("Get all booking ids")
    public ValidatableResponse getAllId() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_BOOKING)
                .then().statusCode(200);
    }
    @Step("Get Single bookingId using bookingId {0}")
    public ValidatableResponse getBookingBySingleId(int bookingId){
        return SerenityRest.given().log().all()
                .pathParam("bookingId", bookingId)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then().statusCode(200).log().all();
    }
    @Step("Get single booking by firstname using firstname {0}")
    public ValidatableResponse getBookingByFirstName(String firstName){
        return SerenityRest.given().log().all()
                .pathParam("firstName", firstName)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_FIRSTNAME)
                .then().log().all()
                .statusCode(200)
                .extract()
                .path("findAll{it.firstname == '" +firstName + "').get{0}");
    }
    @Step("Get Ping Health Check")
    public ValidatableResponse getPing() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.PING)
                .then().statusCode(201).log().all();
    }



}