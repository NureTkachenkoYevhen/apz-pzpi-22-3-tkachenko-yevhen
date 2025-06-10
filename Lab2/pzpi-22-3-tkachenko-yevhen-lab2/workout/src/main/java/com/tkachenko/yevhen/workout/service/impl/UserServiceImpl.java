package com.tkachenko.yevhen.workout.service.impl;

import com.tkachenko.yevhen.workout.dto.UserDto;
import com.tkachenko.yevhen.workout.entity.User;
import com.tkachenko.yevhen.workout.entity.Session;
import com.tkachenko.yevhen.workout.exception.ResourceNotFoundException;
import com.tkachenko.yevhen.workout.mapper.UserMapper;
import com.tkachenko.yevhen.workout.repository.UserRepository;
import com.tkachenko.yevhen.workout.repository.SessionRepository;
import com.tkachenko.yevhen.workout.repository.RecommendationRepository;
import com.tkachenko.yevhen.workout.repository.MetricRepository;
import com.tkachenko.yevhen.workout.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionRepository sessionRepository;
    private final RecommendationRepository recommendationRepository;
    private final MetricRepository metricRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, SessionRepository sessionRepository, RecommendationRepository recommendationRepository, MetricRepository metricRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRepository = sessionRepository;
        this.recommendationRepository = recommendationRepository;
        this.metricRepository = metricRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);

        user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User is not exist with given id : " + userId)
                );
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User is not exist with given id : " + userId));

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPasswordHash(passwordEncoder.encode(updatedUser.getPassword()));
        }

        User updatedUserObj = userRepository.save(user);
        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User is not exist with given id : " + userId)
                );

        // Delete all sessions and their related data
        List<Session> userSessions = sessionRepository.findByUserUserId(userId);
        for (Session session : userSessions) {
            // Delete recommendations for this session
            recommendationRepository.deleteBySessionSessionId(session.getSessionId());
            // Delete metrics for this session
            metricRepository.deleteBySessionSessionId(session.getSessionId());
            // Delete the session itself
            sessionRepository.deleteById(session.getSessionId());
        }

        // Finally delete the user
        userRepository.deleteById(userId);
    }
}
