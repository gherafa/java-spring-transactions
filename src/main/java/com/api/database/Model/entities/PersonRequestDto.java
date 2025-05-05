package com.api.database.Model.entities;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonRequestDto {
    private Long Id;
    private String name;
    private String description;
    private Long balance;

    public Person convertToPersonEntity() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this, Person.class);
    }
}
