package app.dateme.article;

import common.module.jpa.BaseJpaPO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticlePO extends BaseJpaPO {
}
