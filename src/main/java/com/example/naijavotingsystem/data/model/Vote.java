package com.example.naijavotingsystem.data.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
    public class Vote {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "candidate_id")
        private Candidate candidate;
        // getters and setters
    }

