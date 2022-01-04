package com.example.pollingapplication.security;

import java.lang.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.TYPE})
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
