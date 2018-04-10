package com.company.repositories;

import com.company.models.User;

public interface UserRepository extends CrudRepository<User> {
    User findOneByName(String name);
}
