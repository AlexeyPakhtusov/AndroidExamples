package ru.gdgkazan.githubmvp.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Alexey Pakhtusov
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewScope { }
