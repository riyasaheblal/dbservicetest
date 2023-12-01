package com.sl.dbs.util;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;


public class TestString {


    public static void main(String[] args) {
        String inputString = "{movie_name=Tails, movie_description=Thriller, movie_releasedate=2023-09-12, movie_rating=4.5}";

        // Replace = with : and add double quotation marks around keys and values
        inputString = inputString.replaceAll("([^,]+)=([^,]+)", "\"$1\":\"$2\"");

        // Wrap with curly braces to form valid JSON
        inputString = "{" + inputString + "}";

        // Use Jackson ObjectMapper for pretty-printing (optional)
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object jsonNode = objectMapper.readValue(inputString, Object.class);
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String jsonString = "{\"movie_name\": \"Movie A\", \"movie_description\": \"Description of Movie A\", \"movie_releasedate\": \"2023-09-12\", \"movie_rating\": \"4.5\"}";
//
//        // Create a JsonReader from the string
//        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));
//
//        // Read the JSON data into a JsonObject
//        JsonObject jsonObject = jsonReader.readObject();
//
//        // Now, you can access values using keys
//        String value1 = jsonObject.getString("movie_name");
//        String value2 = jsonObject.getString("movie_description");
//        String value3 = jsonObject.getString("movie_releasedate");
//        String value4 = jsonObject.getString("movie_rating");
//
//        System.out.println("value1: " + value1); // Output: value1
//        System.out.println("value2: " + value2); // Output: value2
//        System.out.println("value3: " + value3); // Output: value2
//        System.out.println("value4: " + value4); // Output: value2

        // Close the JsonReader when done
//        jsonReader.close();
//        String s1 = " ";
//
//        System.out.println(s1.isEmpty());
    }
}
