package common.module.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AppPageParam {
    private Integer pageSize;
    private Integer pageIndex;
    private String orderBy;
    private Boolean asc;
}
