package com.api.database.Model.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Person")
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String description;
    private Long balance;
    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    public PersonResponseDto convertToPersonResponseDto() {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(this, PersonResponseDto.class);
    }
}
