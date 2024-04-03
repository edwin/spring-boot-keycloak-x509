package com.edw.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *     com.edw.config.CustomJwtAuthenticationConverter
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 03 Apr 2024 21:19
 */
@Component
public class CustomJwtAuthenticationConverter  implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        return new JwtAuthenticationToken(source);
    }
}
