package com.example.naijavotingsystem.service;

import com.example.naijavotingsystem.data.model.Candidate;
import com.example.naijavotingsystem.data.model.User;
import com.example.naijavotingsystem.data.model.Vote;
import com.example.naijavotingsystem.data.repository.CandidateRepository;
import com.example.naijavotingsystem.data.repository.UserRepository;
import com.example.naijavotingsystem.data.repository.VoteRepository;
import com.example.naijavotingsystem.dtos.ResultDto;
import com.example.naijavotingsystem.dtos.UserDto;
import com.example.naijavotingsystem.exception.AlreadyVotedException;
import com.example.naijavotingsystem.exception.EmailAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VotingService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public User register(UserDto userDto) throws EmailAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new EmailAlreadyExistException("There is already an account with that email address: " + userDto.getEmail());
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User update(UserDto userDto, String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return userRepository.save(user);
    }

    @Override
    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Vote castVote(Long candidateId, String email) throws AlreadyVotedException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }
        Vote existingVote = voteRepository.findByUserId(user.getId()).orElse(null);
        if (existingVote != null) {
            throw new AlreadyVotedException("You have already cast your vote!");
        }
        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);
        if (candidate == null) {
            return null;
        }
        Vote vote = new Vote();
        vote.setUser(user);
        vote.setCandidate(candidate);
        return voteRepository.save(vote);
    }

    @Override
    public List<ResultDto> getResults() {
        List<Candidate> candidates = candidateRepository.findAll();
        List<ResultDto> results = new ArrayList<>();
        Long totalVotes = voteRepository.countDistinctByUserIdIsNotNull();
        for (Candidate candidate : candidates) {
            Long voteCount = voteRepository.countByCandidateId(candidate.getId());
            Double percentage = totalVotes == 0 ? 0 : ((double) voteCount / totalVotes) * 100;
            results.add(new ResultDto(candidate.getName(), voteCount, percentage));
        }
        return results;
    }

    private boolean emailExists(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
}
