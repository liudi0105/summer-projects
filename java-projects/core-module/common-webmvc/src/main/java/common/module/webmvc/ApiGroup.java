package common.module.webmvc;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.POST)
public @interface ApiGroup {
    @AliasFor(attribute = "path", annotation = RequestMapping.class)
    String path();
}
