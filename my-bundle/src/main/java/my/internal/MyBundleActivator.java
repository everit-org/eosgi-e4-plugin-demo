package my.internal;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import my.MyService;

public class MyBundleActivator implements BundleActivator {

    private ServiceRegistration<MyService> serviceRegistration;

    @Override
    public void start(final BundleContext context) throws Exception {
        serviceRegistration = context.registerService(MyService.class, new MyServiceImpl(), new Hashtable<>());
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
    }

}
