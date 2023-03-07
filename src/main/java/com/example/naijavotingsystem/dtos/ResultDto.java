package com.example.naijavotingsystem.dtos;

import lombok.*;

@Setter
@Getter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private String candidateName;
    private Long voteCount;
    private Double percentage;
}
