package com.awesome.business.template.controller.util;

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public class Logger {

    public void log(Object obj) {
        System.out.println(obj);
    }

    public void info(String str, Object... args) {
        System.out.printf(str, args);
    }

    public void error(String str, Object... args) {
        System.err.printf(str, args);
    }
}
