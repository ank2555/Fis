package com.Scenario1.Test;

import com.google.gson.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;

public class Scenario2

{



    @Test
    public  void ApiDesk()
    {


        baseURI="https://api.coindesk.com/";

      String response=  given().

                get("v1/bpi/currentprice.json")
                .then().
           log().all().
                statusCode(200)
                .extract().response().asPrettyString();



        JsonParser jsonParser = new JsonParser();
        JsonElement jsonObject = JsonParser.parseString(response);

        Set<Map.Entry<String, JsonElement>> bpi = jsonObject.getAsJsonObject().getAsJsonObject("bpi").entrySet();



         //Fetching all BPI present in response Payload
        for ( Map.Entry<String, JsonElement> s: bpi)
        {
            System.out.println(s.getKey());
        }

        //Putting Assertion for GBP description entry
        String gbpDescription= jsonObject.getAsJsonObject().getAsJsonObject( "bpi").getAsJsonObject("GBP").get("description").getAsString();
        System.out.println(gbpDescription);
        Assert.assertEquals(gbpDescription,"British Pound Sterling");




    }

}
