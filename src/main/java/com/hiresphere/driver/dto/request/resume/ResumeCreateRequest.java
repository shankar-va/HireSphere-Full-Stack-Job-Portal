package com.hiresphere.driver.dto.request.resume;


import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;

import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;


import java.util.Map;


@Data

@Builder

@AllArgsConstructor

@NoArgsConstructor

public class ResumeCreateRequest {


    @NotNull

    private Integer jobSeekerId;


    private String summary;


    @NotBlank

    private String education;


    @NotNull

    private Map<String, String> projects;


    @NotNull

    @PositiveOrZero

    private Double experience;


}
