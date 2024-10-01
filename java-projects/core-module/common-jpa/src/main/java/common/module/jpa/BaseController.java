package common.module.jpa;

public interface BaseController<S, D extends BaseDTO> {

    BaseJpaService<?, ?, D> getService();

    default AppPageResult<D> listPaged(D param) {
        return getService().getRepo().pageQuery(param, QueryBuilder.getInstance());
    }
}
