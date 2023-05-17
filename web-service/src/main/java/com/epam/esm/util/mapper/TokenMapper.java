package com.epam.esm.util.mapper;

import com.epam.esm.dto.TokenDto;
import com.epam.esm.entity.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper extends BaseMapper<Token, TokenDto> {

}
