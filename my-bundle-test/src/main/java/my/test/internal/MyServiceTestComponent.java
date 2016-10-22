package my.test.internal;

import org.everit.osgi.dev.testrunner.TestDuringDevelopment;
import org.everit.osgi.dev.testrunner.TestRunnerConstants;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.Service;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.extender.ExtendComponent;
import org.junit.Assert;
import org.junit.Test;

import my.MyService;


@Component(componentId = "MyServiceTestComponent")
@Service
@ExtendComponent
@StringAttributes({
    @StringAttribute(
                     attributeId = TestRunnerConstants.SERVICE_PROPERTY_TESTRUNNER_ENGINE_TYPE,
                     defaultValue = "junit4"),
    @StringAttribute(
                     attributeId = TestRunnerConstants.SERVICE_PROPERTY_TEST_ID,
                     defaultValue = "MyServiceTest")})
@TestDuringDevelopment
public class MyServiceTestComponent {

    private MyService myService;

    @ServiceRef(defaultValue = "")
    public void setMyService(final MyService myService) {
        this.myService = myService;
    }

    @Test
    public void testFoo() {
        String sayHello = myService.sayHello("John");
        Assert.assertEquals("Hello John", sayHello);
    }

}
