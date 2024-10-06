package common.module.jpa;

import common.module.webmvc.Api;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BaseController<D extends BaseDTO> {

    BaseJpaService<?, ?, D> getService();

    @Api(path = "list-paged")
    default AppPageResult<D> listPaged(@RequestBody D param) {
        return getService().getRepo().pageQuery(param, QueryBuilder.getInstance());
    }

    @Api(path = "create-or-update")
    default D createOrUpdate(@RequestBody D param) {
        D orUpdate1 = getService().createOrUpdate(param);
        List<?> all = getService().getRepo().findAll();
        return orUpdate1;
    }
}
