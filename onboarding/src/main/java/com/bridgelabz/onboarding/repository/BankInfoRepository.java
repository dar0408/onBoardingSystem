package com.bridgelabz.onboarding.repository;

import com.bridgelabz.onboarding.entity.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {}