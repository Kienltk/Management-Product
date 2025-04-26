package com.example.demo.helper.base.construct;

import java.util.List;

public abstract class RestfullRService <T,P>{
    public abstract List<T> getAll(P p);

    public abstract T get(Integer id);
}
