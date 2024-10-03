package common.module.webmvc;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(WebMvcConfiguration.class)
public @interface EnableWebMvc {
}
