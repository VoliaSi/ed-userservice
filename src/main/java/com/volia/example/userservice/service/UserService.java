package com.volia.example.userservice.service;

import com.volia.example.userservice.exception.UserNotFoundException;
import com.volia.example.userservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public abstract class UserService<
        U extends User,
        CreateDto,
        UpdateDto,
        ResponseDto,
        M> {

    protected final JpaRepository<U, Long> repository;
    protected final M mapper;
    protected final PasswordEncoder passwordEncoder;

    public ResponseDto create(CreateDto dto) {
        U user = mapFromCreate(dto);
        user.setPassword(passwordEncoder.encode(getPasswordFromCreateDto(dto)));
        U saved = repository.save(user);
        return mapToResponse(saved);
    }

    public ResponseDto update(Long id, UpdateDto dto) {
        U user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        updateFromDto(dto, user);
        U saved = repository.save(user);
        return mapToResponse(saved);
    }

    public ResponseDto getById(Long id) {
        U user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return mapToResponse(user);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }

    protected abstract U mapFromCreate(CreateDto dto);
    protected abstract String getPasswordFromCreateDto(CreateDto dto);
    protected abstract void updateFromDto(UpdateDto dto, U user);
    protected abstract ResponseDto mapToResponse(U user);
}
