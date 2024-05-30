package ru.gb.SpringAOP_.HW8_with_monitorring._HW11.aspect;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface TrackUserAction {
}