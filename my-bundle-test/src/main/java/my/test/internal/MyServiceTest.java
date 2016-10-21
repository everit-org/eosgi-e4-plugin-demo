package my.test.internal;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.dev.testrunner.TestDuringDevelopment;
import org.everit.osgi.dev.testrunner.TestRunnerConstants;
import org.junit.Assert;
import org.junit.Test;

import my.MyService;

@Component(immediate = true)
@Properties({
    @Property(name = TestRunnerConstants.SERVICE_PROPERTY_TESTRUNNER_ENGINE_TYPE, value = "junit4"),
    @Property(name = TestRunnerConstants.SERVICE_PROPERTY_TEST_ID, value = "MyServiceTest")})
@Service(MyServiceTest.class)
@TestDuringDevelopment
public class MyServiceTest {

    @Reference(bind = "setMyService")
    private MyService myService;

    protected void setMyService(final MyService myService) {
        this.myService = myService;
    }

    @Test
    public void testFoo() {
        String bar = "bar";
        String actual = myService.foo(bar);
        Assert.assertEquals(bar, actual);
    }

}
