package org.example.timestamp_api.controllers;

import lombok.RequiredArgsConstructor;
import org.example.timestamp_api.dto.TimeStampObject;
import org.example.timestamp_api.services.TimeStampService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TimeStampController {
    private final TimeStampService service;



    @GetMapping
    public TimeStampObject getTimeStampNow(){
        return service.getTimeNow();
    }

    @GetMapping("/{date}")
    public ResponseEntity<TimeStampObject> getUTCTime(@PathVariable(required = false) String date) {
        if (date == null) {
            return new ResponseEntity<>(getTimeStampNow(), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(service.checkDate(date), HttpStatus.OK);
    }


}
