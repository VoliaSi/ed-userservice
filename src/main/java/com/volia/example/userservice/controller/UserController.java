package com.volia.example.userservice.controller;

import com.volia.example.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public abstract class UserController<Dto> {

    protected abstract UserService<?, Dto, ?> getService();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dto create(@RequestBody @Valid Dto dto) {
        return getService().create(dto);
    }

    @PutMapping("/{id}")
    public Dto update(@PathVariable Long id, @RequestBody @Valid Dto dto) {
        return getService().update(id, dto);
    }

    @GetMapping("/{id}")
    public Dto get(@PathVariable Long id) {
        return getService().getById(id);
    }

    @GetMapping
    public java.util.List<Dto> getAll() {
        return getService().getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        getService().delete(id);
    }
}
