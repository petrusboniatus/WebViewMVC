package com.awesome.business.template.api;

/**
 * Created by Carlos Couto Cerdeira on 3/22/17.
 */
public interface ViewHandler {

    View loadView(String htmlUrl);

    void show(View v);

    void close();
}
