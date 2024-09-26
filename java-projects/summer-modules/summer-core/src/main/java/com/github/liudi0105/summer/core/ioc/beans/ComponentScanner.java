package common.module.summer.core.ioc.beans;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Set;

public class ComponentScanner {

    private final Set<String> packages = Sets.newHashSet();

    public void addPkg(String... pkg) {
        if (pkg == null) {
            return;
        }
        packages.addAll(Arrays.stream(pkg).toList());
    }

    public void scan(String pkg) {
    }

}
