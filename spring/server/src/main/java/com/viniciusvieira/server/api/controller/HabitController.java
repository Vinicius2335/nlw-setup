package com.viniciusvieira.server.api.controller;

import com.viniciusvieira.server.api.representation.model.requestbody.HabitsRequestBody;
import com.viniciusvieira.server.api.representation.model.responsebody.HabitResponseBody;
import com.viniciusvieira.server.domain.service.HabitsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/habit")
@RestController
public class HabitController {
    private final HabitsService habitsService;

    @GetMapping
    public ResponseEntity<String> helloHabit(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Hello Habit");
    }

    @PostMapping
    public ResponseEntity<HabitResponseBody> createNewHabit(@RequestBody @Valid HabitsRequestBody habitsRequest){
        HabitResponseBody newHabit = habitsService.createNewHabit(habitsRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newHabit);
    }
}
