package com.bci.cl.user_ms.mapper;

import com.bci.cl.user_ms.dto.PhoneDTO;
import com.bci.cl.user_ms.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhoneMapper {
    Phone toPhoneEntity(PhoneDTO dto);

    PhoneDTO toPhoneDTO(Phone phone);
}
