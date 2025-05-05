package com.api.database.Model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponseDto {
    private Long Id;
    private String name;
    private String description;
    private Long balance;
}
