package common.module.webmvc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppBeans {

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

}
