package com.example.naijavotingsystem.controller;

import com.example.naijavotingsystem.data.model.Candidate;
import com.example.naijavotingsystem.data.model.User;
import com.example.naijavotingsystem.data.model.Vote;
import com.example.naijavotingsystem.dtos.ResultDto;
import com.example.naijavotingsystem.dtos.UserDto;
import com.example.naijavotingsystem.exception.AlreadyVotedException;
import com.example.naijavotingsystem.exception.EmailAlreadyExistException;
import com.example.naijavotingsystem.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class VotingController {

    private final VotingService votingService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) throws EmailAlreadyExistException {
        User user = votingService.register(userDto);
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@RequestBody UserDto userDto, Principal principal) {
        User user = votingService.update(userDto, principal.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/candidates")
    public ResponseEntity<List<Candidate>> getCandidates() {
        List<Candidate> candidates = votingService.getCandidates();
        return ResponseEntity.ok(candidates);
    }

    @PostMapping("/vote/{candidateId}")
    public ResponseEntity<Vote> castVote(@PathVariable Long candidateId, Principal principal) throws AlreadyVotedException {
        Vote vote = votingService.castVote(candidateId, principal.getName());
        if (vote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vote);
    }

    @GetMapping("/results")
    public ResponseEntity<List<ResultDto>> getResults() {
        List<ResultDto> results = votingService.getResults();
        return ResponseEntity.ok(results);
    }
}
