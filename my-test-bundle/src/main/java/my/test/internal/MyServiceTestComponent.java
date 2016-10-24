package my.test.internal;

import org.everit.osgi.dev.testrunner.TestDuringDevelopment;
import org.everit.osgi.dev.testrunner.TestRunnerConstants;
import org.everit.osgi.ecm.annotation.Activate;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.Service;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.extender.ExtendComponent;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServiceTestComponent.class);

    private MyService myService;

    @Activate
    public void activate() {
        LOGGER.info("+++++++++++++++++++++++++++++++");
        LOGGER.info(MyServiceTestComponent.class.getSimpleName() + " activated");
        LOGGER.info("+++++++++++++++++++++++++++++++");
    }

    @ServiceRef(defaultValue = "")
    public void setMyService(final MyService myService) {
        this.myService = myService;
    }

    @Test
    public void testSayHello() {
        String sayHello = myService.sayHello("John");
        Assert.assertEquals("Hello John", sayHello);
    }

    @Test
    public void testSayHelloArgument() {

        try {
            myService.sayHello(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertTrue("name cannot be null or empty".equals(e.getMessage()));
        }

        try {
            myService.sayHello("");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            Assert.assertTrue("name cannot be null or empty".equals(e.getMessage()));
        }
    }

}
