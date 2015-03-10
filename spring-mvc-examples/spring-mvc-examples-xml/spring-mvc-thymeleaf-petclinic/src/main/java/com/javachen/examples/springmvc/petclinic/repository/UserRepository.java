package com.javachen.examples.springmvc.petclinic.repository;

import com.javachen.examples.springmvc.petclinic.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author <a href="mailto:june.chan@foxmail.com">june</a>.
 * @date 2014-12-05 14:24.
 */
public interface UserRepository{
    List<User> findByName(String name);

    @Query("from User where name like ?1||'%'")
    List<User> findByNameLike(String name);

    Page<User> findByName(String name, Pageable pageable);

    User findOne(Integer id);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    User save(User user);

    void delete(Integer id);
}
