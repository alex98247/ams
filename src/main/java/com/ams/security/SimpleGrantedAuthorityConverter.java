package com.ams.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter
public class SimpleGrantedAuthorityConverter implements AttributeConverter<SimpleGrantedAuthority, String> {

    @Override
    public String convertToDatabaseColumn(SimpleGrantedAuthority authority) {
        return Optional.ofNullable(authority)
                .map(SimpleGrantedAuthority::getAuthority)
                .orElse(null);
    }

    @Override
    public SimpleGrantedAuthority convertToEntityAttribute(String role) {
        return Optional.ofNullable(role)
                .map(SimpleGrantedAuthority::new)
                .orElse(null);
    }
}
