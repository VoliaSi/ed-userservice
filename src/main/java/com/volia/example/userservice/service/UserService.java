package com.volia.example.userservice.service;

import com.volia.example.userservice.exception.EmailAlreadyExistsException;
import com.volia.example.userservice.exception.UserNotFoundException;
import com.volia.example.userservice.model.User;
import com.volia.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public abstract class UserService<
        U extends User,
        Dto,
        M> {

    protected final UserRepository<U> repository;
    protected final M mapper;

    @Transactional
    public Dto create(Dto dto) {
        U user = mapFromDto(dto);

        if (repository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }

        U saved = repository.save(user);
        return mapToDto(saved);
    }

    @Transactional
    public Dto update(Long id, Dto dto) {
        U user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (!user.getEmail().equals(dtoEmail(dto)) && repository.existsByEmail(dtoEmail(dto))) {
            throw new EmailAlreadyExistsException(dtoEmail(dto));
        }

        updateFromDto(dto, user);
        U saved = repository.save(user);
        return mapToDto(saved);
    }

    public Dto getById(Long id) {
        U user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return mapToDto(user);
    }

    public java.util.List<Dto> getAll() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }

    protected abstract U mapFromDto(Dto dto);
    protected abstract void updateFromDto(Dto dto, U user);
    protected abstract Dto mapToDto(U user);

    // Abstract method to extract email from DTO
    protected abstract String dtoEmail(Dto dto);
}
