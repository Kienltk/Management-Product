package com.example.demo.helper.base.construct;

import com.example.demo.helper.base.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



public abstract class RestfullController<P, S, U> {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public abstract ResponseObject<?> getPaginate(@ModelAttribute P p);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public abstract ResponseObject<?> get(@PathVariable Integer id);

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public abstract ResponseObject<?> store(@Validated @RequestBody S s);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public abstract ResponseObject<?> update(@PathVariable Integer id, @Validated @RequestBody U u);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public abstract ResponseObject<?> destroy(@PathVariable Integer id);
}
