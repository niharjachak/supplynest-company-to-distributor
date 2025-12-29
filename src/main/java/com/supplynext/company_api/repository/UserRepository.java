package com.supplynext.company_api.repository;

import com.supplynext.company_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    public  User findByEmail(String email);
}
