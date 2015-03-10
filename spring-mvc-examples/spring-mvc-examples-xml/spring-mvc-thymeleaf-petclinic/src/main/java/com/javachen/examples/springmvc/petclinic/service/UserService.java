package com.javachen.examples.springmvc.petclinic.service;

import com.javachen.examples.springmvc.petclinic.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {

    public List<User> findByName(String name) throws DataAccessException;

    public User findById(int id) throws DataAccessException;

    public User save(User user) throws DataAccessException;

    public void delete(Integer id) throws DataAccessException;

    Page<User> findByName(String name,Pageable p)throws DataAccessException;

    List<User> findAll() throws DataAccessException;

    Page<User> findAll(Pageable p) throws DataAccessException;

}