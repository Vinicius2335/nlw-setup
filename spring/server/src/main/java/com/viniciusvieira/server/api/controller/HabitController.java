package com.viniciusvieira.server.api.controller;

import com.viniciusvieira.server.api.representation.model.requestbody.HabitsRequestBody;
import com.viniciusvieira.server.api.representation.model.responsebody.DetailOfTheDayResponseBody;
import com.viniciusvieira.server.api.representation.model.responsebody.HabitResponseBody;
import com.viniciusvieira.server.core.util.DataUtil;
import com.viniciusvieira.server.domain.model.Habits;
import com.viniciusvieira.server.domain.service.BuscarDetalhesDoDiaService;
import com.viniciusvieira.server.domain.service.RegistrarHabitService;
import com.viniciusvieira.server.domain.service.ToggleHabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Log4j2
public class HabitController {
    private final RegistrarHabitService registrarHabitService;
    private final BuscarDetalhesDoDiaService buscarDetalhesDoDiaService;
    private final ToggleHabitService toggleHabitService;

    @GetMapping("/habit")
    public ResponseEntity<String> helloHabit(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Hello Habit");
    }

    @PostMapping("/habit")
    public ResponseEntity<HabitResponseBody> createNewHabit(@RequestBody @Valid HabitsRequestBody habitsRequest){
        log.info("Criando novo hábito...");
        HabitResponseBody newHabit = registrarHabitService.createNewHabit(habitsRequest);
        log.info("Novo hábito criado com sucesso...");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newHabit);
    }

    // BUG não está funcionando
    @GetMapping("/day")
    public ResponseEntity<DetailOfTheDayResponseBody> buscarHabitPorData(@RequestParam String date){
        log.info("Buscando hábitos por dia...");
        OffsetDateTime dateAsOffsetDateTime = DataUtil.converterStringEmOffsetDateTime(date);
        DetailOfTheDayResponseBody responseBody = buscarDetalhesDoDiaService.findDetailsOfTheDay(dateAsOffsetDateTime);
        log.info(dateAsOffsetDateTime);
        log.info("Buscar hábitos por data realizada com sucesso...");
        log.info(responseBody);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    // TODO excluir dps
    @GetMapping("habits")
    public ResponseEntity<List<Habits>> getAllHabits(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buscarDetalhesDoDiaService.getAllHabits());
    }

    @PutMapping("/habits/{idHabit}/toggle")
    public ResponseEntity<Void> toggleHabit(@PathVariable UUID idHabit){
        toggleHabitService.toggleHabit(idHabit);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
