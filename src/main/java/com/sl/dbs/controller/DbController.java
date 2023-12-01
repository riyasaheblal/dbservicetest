package com.sl.dbs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sl.dbs.model.Request;
import com.sl.dbs.service.DbServiceConn;
import com.sl.dbs.service.DbServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Log4j2
@RestController
@RequestMapping(path = "/dbservice")
public class DbController {

    @Autowired
    DbServiceConn dbService;

    @Autowired
    DbServiceImpl dbService1;

    @Operation(summary="get all data")

    @PostMapping(value = "/Getdata")
    public ResponseEntity<String> getData(@RequestBody Request request) throws JsonProcessingException, SQLException {

        try {
            ResponseEntity<String> response = dbService.getData(request);
            return response;
        }
        catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }    }

    @Operation(summary="here we are going to insert and update")
    @PostMapping(value = "/writeData")
    public ResponseEntity<String> insertData(@RequestBody Request request) throws JsonProcessingException, SQLException {

        try {
            ResponseEntity<String> response = dbService.getData(request);
            return response;
        }
        catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }    }


    @Operation(summary="json")
    @PostMapping(value = "/PostComplexData")
    public ResponseEntity<String> PostComplexData(@RequestBody Request request) throws JsonProcessingException, SQLException {
        try {
            ResponseEntity<String> response = dbService.getData(request);
            return response;
        }
        catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }    }

    @Operation(summary="sp multiple table")
    @PostMapping(value = "/GetMultipleDataSet")
    public ResponseEntity<String> GetMultipleDataSet(@RequestBody Request request) throws JsonProcessingException, SQLException {
        try {
            ResponseEntity<String> response = dbService.getData(request);
            log.info(response);
            return response;
        }
        catch (Exception e) {
            log.error(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }    }

@PostMapping(value = "/data")
    public ResponseEntity<String> getDb(@RequestBody Request request) throws Exception {
        return dbService1.getData(request);
}

}
