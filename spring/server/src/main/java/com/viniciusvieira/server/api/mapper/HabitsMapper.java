package com.viniciusvieira.server.api.mapper;

import com.viniciusvieira.server.api.representation.model.responsebody.HabitResponseBody;
import com.viniciusvieira.server.domain.model.Habits;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HabitsMapper {
    private final ModelMapper modelMapper;

    public HabitResponseBody toHabitsResponseBody(Habits habits){
        return modelMapper.map(habits, HabitResponseBody.class);
    }
}
