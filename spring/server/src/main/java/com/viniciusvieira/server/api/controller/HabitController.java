package com.viniciusvieira.server.api.controller;

import com.viniciusvieira.server.api.representation.model.requestbody.HabitsRequestBody;
import com.viniciusvieira.server.api.representation.model.responsebody.DetailOfTheDayResponseBody;
import com.viniciusvieira.server.api.representation.model.responsebody.HabitResponseBody;
import com.viniciusvieira.server.api.representation.model.responsebody.SummaryResponseBody;
import com.viniciusvieira.server.core.util.DataUtil;
import com.viniciusvieira.server.domain.service.BuscarDetalhesDoDiaService;
import com.viniciusvieira.server.domain.service.BuscarSummaryService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Log4j2
public class HabitController {
    private final RegistrarHabitService registrarHabitService;
    private final BuscarDetalhesDoDiaService buscarDetalhesDoDiaService;
    private final ToggleHabitService toggleHabitService;
    private final BuscarSummaryService buscarSummaryService;

    @PostMapping("/habits")
    public ResponseEntity<HabitResponseBody> createNewHabit(@RequestBody @Valid HabitsRequestBody habitsRequest){
        log.info("Criando novo hábito...");
        HabitResponseBody newHabit = registrarHabitService.createNewHabit(habitsRequest);
        log.info("Novo hábito criado com sucesso...");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newHabit);
    }

    @GetMapping("/day")
    public ResponseEntity<DetailOfTheDayResponseBody> buscarHabitPorData(@RequestParam OffsetDateTime date){
        log.info("Buscando hábitos por dia...");
        DetailOfTheDayResponseBody responseBody = buscarDetalhesDoDiaService.findDetailsOfTheDay(date);
        log.info("Buscar hábitos por data realizada com sucesso...");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PutMapping("/habits/{idHabit}/toggle")
    public ResponseEntity<Void> toggleHabit(@PathVariable UUID idHabit){
        toggleHabitService.toggleHabit(idHabit);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/summary")
    public ResponseEntity<List<SummaryResponseBody>> summaryHabit(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buscarSummaryService.summaryHabit());
    }
}
