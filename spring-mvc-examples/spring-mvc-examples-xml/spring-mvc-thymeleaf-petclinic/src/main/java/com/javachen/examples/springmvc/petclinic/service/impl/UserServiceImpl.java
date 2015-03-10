package com.javachen.examples.springmvc.petclinic.service.impl;

import com.javachen.examples.springmvc.petclinic.model.User;
import com.javachen.examples.springmvc.petclinic.repository.UserRepository;
import com.javachen.examples.springmvc.petclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected Md5PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public User findById(int id) throws DataAccessException {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public User save(User user) throws DataAccessException {
        if (user.isNew()) {
            user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
                    user.getName()));
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> findAll() throws DataAccessException {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) throws DataAccessException {
        userRepository.delete(id);
    }

    @Override
    public List<User> findByName(String name)
            throws DataAccessException {
        return userRepository.findByName(name);
    }

    @Override
    public Page<User> findByName(String name, Pageable pageable)
            throws DataAccessException {
        return userRepository.findByName(name, pageable);
    }

    @Override
    public Page<User> findAll(Pageable pageable) throws DataAccessException {
        return userRepository.findAll(pageable);
    }

}