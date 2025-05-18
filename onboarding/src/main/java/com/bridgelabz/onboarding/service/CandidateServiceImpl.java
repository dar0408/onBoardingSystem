package com.bridgelabz.onboarding.service;

import com.bridgelabz.onboarding.config.RabbitMQConfig;
import com.bridgelabz.onboarding.dto.CandidateDTO;
import com.bridgelabz.onboarding.dto.ResponseDTO;
import com.bridgelabz.onboarding.entity.Candidate;
import com.bridgelabz.onboarding.exception.CandidateNotFoundException;
import com.bridgelabz.onboarding.exception.InvalidStatusException;
import com.bridgelabz.onboarding.repository.CandidateRepository;
import com.bridgelabz.onboarding.util.EmailTemplateUtil;
import com.bridgelabz.onboarding.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository repository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public List<CandidateDTO> getAllCandidates() {
        return repository.findAll()
                .stream()
                .map(MapperUtil::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidateDTO> getCandidatesByStatus(String status) {
        return repository.findByStatus(status)
                .stream()
                .map(MapperUtil::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateDTO getCandidateById(Long id) {
        Candidate c = repository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        return MapperUtil.toDTO(c);
    }

    @Override
    public long countCandidatesByStatus(String status) {
        return repository.countByStatus(status);
    }

    @Override
    @Transactional
    public ResponseDTO<String> updateStatus(Long id, String status) {
        Candidate c = repository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        if (!List.of("APPLIED", "INTERVIEWED", "OFFERED", "ONBOARDED").contains(status)) {
            throw new InvalidStatusException(status);
        }
        c.setStatus(status);
        if ("ONBOARDED".equals(status)) {
            c.setOnboardedDate(LocalDateTime.now());
        }
        repository.save(c);
        return new ResponseDTO<>("Status updated to " + status, null);
    }

    @Override
    public ResponseDTO<String> notifyJobOffer(Long id) {
        Candidate c = repository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));
        c.setStatus("OFFERED");
        repository.save(c);
        rabbitTemplate.convertAndSend(RabbitMQConfig.JOB_OFFER_QUEUE, c);
        return new ResponseDTO<>("Job offer queued for " + c.getEmail(), null);
    }

    @Override
    @Transactional
    public CandidateDTO createCandidate(CandidateDTO dto) {
        Candidate candidate = MapperUtil.toEntity(dto);
        candidate.setStatus("APPLIED");
        candidate.setAppliedDate(LocalDateTime.now());

        Candidate saved = repository.save(candidate);
        return MapperUtil.toDTO(saved);
    }

}
