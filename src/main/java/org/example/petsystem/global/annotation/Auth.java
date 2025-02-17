package org.example.petsystem.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.example.petsystem.member.domain.MemberRole;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

//    enum MemberRole {ROLE_USER, ROLE_ADMIN};

    MemberRole[] role();
}
