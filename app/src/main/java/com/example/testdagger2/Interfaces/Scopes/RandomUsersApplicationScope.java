package com.example.testdagger2.Interfaces.Scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface RandomUsersApplicationScope {
}
