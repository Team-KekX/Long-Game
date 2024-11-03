package eu.kekx.long_game.service.domain;

import eu.kekx.long_game.domain.User;
import eu.kekx.long_game.domain.workout.CustomMetricType;
import eu.kekx.long_game.persistence.workout.CustomMetricTypeRepository;
import eu.kekx.long_game.presentation.request.CreateCustomMetricTypeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CustomMetricTypeService {
    
    private final CustomMetricTypeRepository customMetricTypeRepository;
    
    @Transactional
    public CustomMetricType createCustomMetricType(User user, CreateCustomMetricTypeRequest dto) {
        Objects.requireNonNull(dto, "CreateCustomMetricTypeRequest must not be null");
        Objects.requireNonNull(user, "User must not be null");
        
        if (customMetricTypeRepository.existsByUserAndName(user, dto.name())) {
            throw new IllegalArgumentException("Custom metric type with name [" + dto.name() + "] already exists for this user");
        }
        
        var customMetricType = new CustomMetricType();
        customMetricType.setUser(user);
        customMetricType.setName(dto.name());
        customMetricType.setDescription(dto.description());
        customMetricType.setDefaultUnit(dto.defaultUnit());
        
        return customMetricTypeRepository.save(customMetricType);
    }
}
