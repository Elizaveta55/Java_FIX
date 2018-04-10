package com.company.repositories;

import com.company.models.Good;

public interface GoodRepositoryJDBC extends CrudRepository<Good> {
    Good findOneByName(String name);
}
