package com.ttknpdev.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ttknpdev.entities.Car;
import com.ttknpdev.entities.Owner;
import com.ttknpdev.log.Logging;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
   how to use Jackson-databind API for binding Java Object to JSON and JSON data to Java Object.
   Using ObjectMapper.writerWithDefaultPrettyPrinter() Method
   Using ObjectMapper.enable(SerializationFeature.INDENT_OUTPUT) Method
*/
public class ControlLogic extends Logging {
    // Create ObjectMapper for getting json string
    private ObjectMapper mapper = new ObjectMapper();
    // Create FileOutputStream for getting location to add json files
    private FileOutputStream fileOutputStream;
    // InputStream for Reading JSON file and convert to java object
    private InputStream fileInputStream;

    public static void main(String[] args) throws IOException {
        // controlLogic.debug("test logging level debug");
        new ControlLogic();
    }

    private void convertObjectToJson() {
        Owner owner1 = new Owner("Peter", (short) 26, 'C', "nice car!!");
        Owner owner2 = new Owner("Mark", (short) 25, 'A', "many options I really love this car");
        Car toyota = new Car();
        toyota.setModelName("toyota yaris 2023");
        toyota.setOrder(1661L);
        toyota.setSales(16000);
        toyota.setPrice(689000D);
        toyota.setOwners(new ArrayList<>(List.of(owner1, owner2)));
        try {
            // Convert List to JSON Array Using Jackson
            // Serialize Object to JSON.
            String jsonArray = mapper.writeValueAsString(toyota.getOwners());
            controlLogic.info(jsonArray);
            // [ {"fullname":"Peter","comment":"nice car!!"} , {"fullname":"Mark","comment":"many options I really love this car"} ]
            // [ "A" , "B" , ... , "N" ]
        } catch (JsonProcessingException e) {
            controlLogic.debug("found some JsonProcessingException exception from method convertObjectToJson() cause is : " + e.getMessage());
            throw new RuntimeException(e);
        }
        // convert object to json string
        controlLogic.info("toyota object Car class store : " + toyota);
        try {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = mapper.writeValueAsString(toyota);
            // String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(toyota);
            controlLogic.info("after use mapper.writerWithDefaultPrettyPrinter().writeValueAsString(toyota) it's retured : \n" + json);
            /*
                {
                  "order" : 1661,
                  "modelName" : "toyota yaris 2023",
                  "price" : 689000.0,
                  "sales" : 16000
                }
                why list owners doesn't show because I got setting @JsonInclude(JsonInclude.Include.NON_NULL)
                another way to set
                // can specify for output
                // mapper.setSerializationInclusion(Include.NON_NULL);
                // mapper.setSerializationInclusion(Include.NON_EMPTY);
            */
            // saveToFile(toyota); // observe args we use pojo to save to be json !!!
        } catch (JsonProcessingException e) {
            controlLogic.debug("found some JsonProcessingException exception from method convertObjectToJson() cause is : " + e.getMessage());
            throw new RuntimeException(e);
        }
/*
        catch (IOException e) {
            controlLogic.debug("found some IOException exception from method convertObjectToJson() cause is : " + e.getMessage());
            throw new RuntimeException(e);
        }
 */
    }

    private void saveToFile(Car car) throws IOException {
        fileOutputStream = new FileOutputStream("B:\\practice-java-one\\LearnJavaCore\\understand-jackson\\json\\my_json.json");
        mapper.writeValue(fileOutputStream, car); // void method
        fileOutputStream.close();
        controlLogic.info("logging after saveToFile() almost done");
    }

    private void readToFile() throws IOException {
        fileInputStream = new FileInputStream("B:\\practice-java-one\\LearnJavaCore\\understand-jackson\\json\\my_json.json");
        Car car = mapper.readValue(fileInputStream, Car.class);
        controlLogic.info("logging after readToFile() almost done");
        controlLogic.info("car stores : "+car);
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // INDENT (v. เยื้อง  , ย่อหน้า) this line's importance for pretty output
        controlLogic.info("convert to json again : "+mapper.writeValueAsString(car));
        fileInputStream.close();
    }


}
