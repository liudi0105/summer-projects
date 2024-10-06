package common.module.jpa;

import org.springframework.data.domain.PageRequest;

public class AppPages {

    public static PageRequest checkPageParam(AppPageParam pageParam) {
        Integer pageIndex = pageParam.getPageIndex();
        Integer pageSize = pageParam.getPageSize();
        if (pageIndex == null || pageIndex < 1) {
            throw new RuntimeException("pageIndex must great than 0");
        }

        if (pageSize == null || pageSize < 1) {
            throw new RuntimeException("pageSize must great than 0");
        }

        return PageRequest.of(pageIndex - 1, pageSize);
    }
}
