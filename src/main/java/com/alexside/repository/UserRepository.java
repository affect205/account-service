package com.alexside.repository;

import com.alexside.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alex on 24.12.2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
}
