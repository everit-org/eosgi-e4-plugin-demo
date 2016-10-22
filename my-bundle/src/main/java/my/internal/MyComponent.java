package my.internal;

import java.util.Map;

import org.everit.osgi.ecm.annotation.Activate;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Service;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.extender.ExtendComponent;

import my.MyService;

@Component(componentId = "MyComponent", configurationPolicy = ConfigurationPolicy.REQUIRE)
@Service
@ExtendComponent
@StringAttributes(@StringAttribute(attributeId = MyComponent.PREFIX))
public class MyComponent implements MyService {

    public static final String PREFIX = "prefix";

    private String prefix;

    @Activate
    public void activate(final Map<String, Object> attributes) {
        prefix = (String) attributes.get(PREFIX);
    }

    @Override
    public String sayHello(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be null or empty");
        }
        return prefix + " " + name;
    }

}
