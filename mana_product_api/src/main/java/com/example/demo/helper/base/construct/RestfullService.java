package com.example.demo.helper.base.construct;

import java.util.List;

public abstract class RestfullService<T, P , S , U> {
    public abstract List<T> getAll(P p);

    public abstract T get(Integer id);

    public abstract T store(S s);

    public abstract T update(Integer id, U  u);

    public abstract T destroy(Integer id);
}