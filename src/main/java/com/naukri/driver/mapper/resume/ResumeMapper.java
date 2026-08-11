package com.naukri.driver.mapper.resume;

import com.naukri.driver.dto.request.resume.ResumeCreateRequest;
import com.naukri.driver.dto.response.resume.ResumeResponse;
import com.naukri.driver.model.entity.Resume;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ResumeMapper {
    Resume toEntity(ResumeCreateRequest request);
    ResumeResponse toResponseDTO(Resume resume);
}
