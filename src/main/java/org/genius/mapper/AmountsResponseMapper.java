package org.genius.mapper;

import org.genius.dto.AmountsResponseDto;
import org.genius.entity.response.AmountsResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface AmountsResponseMapper {

    AmountsResponseDto map(AmountsResponse amountsResponse);

}


