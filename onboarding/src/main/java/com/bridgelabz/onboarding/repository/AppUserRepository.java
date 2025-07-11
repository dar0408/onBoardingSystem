//package com.bridgelabz.onboarding.repository;
//
//import com.bridgelabz.onboarding.entity.AppUser;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.Optional;
//
//public interface AppUserRepository extends JpaRepository<AppUser, Long> {
//    Optional<AppUser> findByUsername(String username);
//    Optional<AppUser> findByEmail(String email);
//
//    boolean existsByUsername(String username);
//    boolean existsByEmail(String email);
//}

package com.bridgelabz.onboarding.repository;

import com.bridgelabz.onboarding.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);
}
