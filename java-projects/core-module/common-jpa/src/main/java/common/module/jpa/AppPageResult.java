package common.module.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AppPageResult<T> {
    private Long totalElements;
    private List<T> content;

    public static <T> AppPageResult<T> of(Long totalElements, List<T> content) {
        return new AppPageResult<>(totalElements, content);
    }

    public <C> AppPageResult<C> map(Function<T, C> function) {
        List<C> collect = this.content.stream().map(function).collect(Collectors.toList());
        return AppPageResult.of(this.totalElements, collect);
    }
}
