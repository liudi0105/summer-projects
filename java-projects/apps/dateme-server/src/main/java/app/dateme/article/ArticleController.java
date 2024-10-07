package app.dateme.article;

import common.module.jpa.CrudController;
import common.module.webmvc.ApiGroup;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@ApiGroup(path = "article")
public class ArticleController implements CrudController<ArticleDTO> {

    @Autowired
    @Getter
    private ArticleService service;

}
