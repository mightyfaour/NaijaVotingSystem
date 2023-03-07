package com.example.naijavotingsystem.service;

import com.example.naijavotingsystem.data.model.Candidate;
import com.example.naijavotingsystem.data.model.User;
import com.example.naijavotingsystem.data.model.Vote;
import com.example.naijavotingsystem.dtos.ResultDto;
import com.example.naijavotingsystem.dtos.UserDto;
import com.example.naijavotingsystem.exception.AlreadyVotedException;
import com.example.naijavotingsystem.exception.EmailAlreadyExistException;

import java.util.List;

public interface VotingService {
    User register(UserDto userDto) throws EmailAlreadyExistException;
    User update(UserDto userDto, String email);
    List<Candidate> getCandidates();
    Vote castVote(Long candidateId, String email) throws AlreadyVotedException, AlreadyVotedException;
    List<ResultDto> getResults();
}
