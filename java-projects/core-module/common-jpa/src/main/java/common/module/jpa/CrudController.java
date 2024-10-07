package common.module.jpa;

import common.module.webmvc.Api;
import org.springframework.web.bind.annotation.RequestBody;

public interface CrudController<D extends BaseDTO> {

    BaseJpaService<?, ?, D> getService();

    @Api(path = "list-paged")
    default AppPageResult<D> listPaged(@RequestBody D param) {
        return getService().getRepo().pageQuery(param, QueryBuilder.getInstance());
    }

    @Api(path = "create-or-update")
    default D createOrUpdate(@RequestBody D param) {
        return getService().createOrUpdate(param);
    }

    @Api(path = "delete-by-id")
    default void deleteById(@RequestBody String id) {
        getService().getRepo().deleteById(id);
    }
}
