package com.company.repositories;

import com.company.models.Good;
import com.company.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {

    Optional<Good> findByName(String name);
    List<Good> findAllByAppUser(User user);
    List<Good> findAll();

}
