package org.example.petsystem.global.annotation;

import static org.example.petsystem.domain.member.MemberRole.ROLE_ADMIN;
import static org.example.petsystem.domain.member.MemberRole.ROLE_USER;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Auth(role = {ROLE_USER, ROLE_ADMIN})
public @interface AuthPrincipal {
}
