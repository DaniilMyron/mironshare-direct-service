package com.miron.directservice.infrastructure.config;

import com.miron.directservice.domain.BasePackageClassesDefiner;
import com.miron.directservice.domain.springAnnotations.DomainRepository;
import com.miron.directservice.domain.springAnnotations.DomainService;
import com.miron.directservice.domain.springAnnotations.DomainUseCase;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(
        basePackageClasses = {BasePackageClassesDefiner.class},
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, DomainRepository.class, DomainUseCase.class})})
public class DomainConfiguration {
}
