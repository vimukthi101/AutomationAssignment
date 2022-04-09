package com.qa.tests;

import com.qa.base.ApiBaseTest;
import com.qa.base.Posts;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.core.IsEqual.equalTo;

public class TaskTwoTest extends ApiBaseTest {
    private static final int SUCCESS_STATUS_CODE = 200;
    private static final int CREATED_STATUS_CODE = 201;
    private static final int USER_ID = new Random().nextInt(10) + 1;
    private static final int NEW_POST_ID = 101;
    private static final String USERS_ENDPOINT = "/users/" + USER_ID;
    private static final String POSTS_ENDPOINT = "/posts";
    private static final String POST_TITLE = "Assignment";
    private static final String POST_BODY = "bar";

    @Description("Verify the get user API")
    @Test
    public void verifyUserSuccessForRandomID() {
        RestAssured
                .given().log().ifValidationFails()
                .when()
                .get(USERS_ENDPOINT)
                .then()
                .log().ifError()
                .contentType(ContentType.JSON)
                .body("id", equalTo(USER_ID))
                .statusCode(SUCCESS_STATUS_CODE);
    }

    @Description("Verify the ID of all posts for same user")
    @Test
    public void verifyPostIdIsValidForAllPostsForSameUser() {
        ArrayList<Integer> ids = RestAssured
                .given().log().ifValidationFails()
                .param("userId", USER_ID)
                .when()
                .get(POSTS_ENDPOINT)
                .then()
                .log().ifError()
                .contentType(ContentType.JSON)
                .statusCode(SUCCESS_STATUS_CODE)
                .extract()
                .response()
                .path("id");
        for(int i=0; i < ids.size(); i++){
            Assert.assertTrue(ids.get(i) > 1 && ids.get(i) < 100);
        }
    }

    @Description("Verify the create post API")
    @Test
    public void verifyCreatePostReturnsSuccessResponse() {
        Posts posts = new Posts(POST_TITLE, POST_BODY, USER_ID);
        RestAssured
                .given().log().ifValidationFails()
                .contentType(ContentType.JSON)
                .body(posts)
                .when()
                .post(POSTS_ENDPOINT)
                .then()
                .log().ifError()
                .contentType(ContentType.JSON)
                .body("userId", equalTo(posts.getUserId()))
                .body("body", equalTo(posts.getBody()))
                .body("title", equalTo(posts.getTitle()))
                .body("id", equalTo(NEW_POST_ID))
                .statusCode(CREATED_STATUS_CODE);
    }
}
