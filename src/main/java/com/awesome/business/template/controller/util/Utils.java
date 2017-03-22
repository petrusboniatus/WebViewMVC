package com.awesome.business.template.controller.util;

import netscape.javascript.JSObject;

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public class Utils {

    public static Object runCallback(JSObject method, Object... args) {
        JSObject window = (JSObject) method.eval("window");
        String script = "this(";
        for (int i = 0; i < args.length; i++) {
            window.setMember("private_arg" + i, args[i]);
            if (i != 0) {
                script += ", ";
            }
            script += "private_arg" + i;
        }
        return method.eval(script + ");");
    }
}
