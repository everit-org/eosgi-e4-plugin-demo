package my.internal;

import my.MyService;

public class MyServiceImpl implements MyService {

    @Override
    public String foo(final String bar) {
        return bar;
    }

}
