package com.viniciusvieira.server.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/habit")
@RestController
public class HabitController {
    @GetMapping
    public ResponseEntity<String> helloHabit(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Hello Habit");
    }
}
