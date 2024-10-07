package common.module.webmvc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValueWrapper<T> {
    private T value;
}
