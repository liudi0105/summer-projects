package app.dateme.article;

import common.module.jpa.BaseJpaService;
import org.springframework.stereotype.Component;

@Component
public class ArticleService extends BaseJpaService<ArticleRepo, ArticlePO, ArticleDTO> {
}
