package com.example.business.service;

public interface CRUD<E, T> {
    T create(E request);
    T readOne(E request);
    T update(E request);
    T delete(E request);
}
