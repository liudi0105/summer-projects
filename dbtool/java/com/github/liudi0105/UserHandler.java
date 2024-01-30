package com.github.liudi0105;

import com.github.liudi0105.annotation.PostMapping;
import com.github.liudi0105.annotation.RestController;

import java.util.Map;

@RestController
public class UserHandler {

    @PostMapping("list-all")
    public Map<Object, Object> listAll() {
        return Map.of();
    }

}
