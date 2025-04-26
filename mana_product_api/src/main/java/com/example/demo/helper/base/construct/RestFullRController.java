package com.example.demo.helper.base.construct;

import com.example.demo.helper.base.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class RestFullRController<P> {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public abstract ResponseObject<?> getPaginate(@ModelAttribute P p);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public abstract ResponseObject<?> get(@PathVariable Integer id);
}
