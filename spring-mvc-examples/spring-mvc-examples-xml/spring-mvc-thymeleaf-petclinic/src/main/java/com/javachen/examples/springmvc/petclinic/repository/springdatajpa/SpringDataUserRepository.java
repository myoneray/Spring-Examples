package com.javachen.examples.springmvc.petclinic.repository.springdatajpa;

import com.javachen.examples.springmvc.petclinic.model.User;
import com.javachen.examples.springmvc.petclinic.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author <a href="mailto:june.chan@foxmail.com">june</a>.
 * @date 2014-12-05 14:25.
 */
public interface SpringDataUserRepository extends UserRepository,JpaRepository<User, Integer>,
        PagingAndSortingRepository<User, Integer>  {
    List<User> findByName(String name);

    @Query("from User where username like ?1||'%'")
    List<User> findByNameLike(String username);

    Page<User> findByName(String name, Pageable p);

}

