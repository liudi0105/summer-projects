package common.module.webmvc;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(method = RequestMethod.POST)
public @interface Api {
    @AliasFor(attribute = "path", annotation = RequestMapping.class)
    String path();
}
