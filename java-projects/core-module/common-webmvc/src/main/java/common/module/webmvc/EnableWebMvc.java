package common.module.webmvc;

import org.springframework.context.annotation.Import;

@Import(WebMvcConfiguration.class)
public @interface EnableWebMvc {
}
