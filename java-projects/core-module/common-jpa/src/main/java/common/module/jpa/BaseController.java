package common.module.jpa;

import common.module.webmvc.Api;
import org.springframework.web.bind.annotation.RequestBody;

public interface BaseController<S, D extends BaseDTO> {

    BaseJpaService<?, ?, D> getService();

    @Api(path = "list-paged")
    default AppPageResult<D> listPaged(@RequestBody D param) {
        return getService().getRepo().pageQuery(param, QueryBuilder.getInstance());
    }

    @Api(path = "create-or-update")
    default D list(@RequestBody D param) {
        return getService().getRepo().createOrUpdate(param);
    }
}
