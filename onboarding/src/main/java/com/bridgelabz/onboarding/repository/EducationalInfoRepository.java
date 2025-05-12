package com.bridgelabz.onboarding.repository;

import com.bridgelabz.onboarding.entity.EducationalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalInfoRepository extends JpaRepository<EducationalInfo, Long> {}