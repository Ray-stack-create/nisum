package com.nisum.demo.controller;

import com.nisum.demo.scope.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/scope")
public class ScopeController {

    @Autowired private SingletonBean singletonBean;
    @Autowired private PrototypeBean prototypeBean;
    @Autowired private RequestBean requestBean;
    @Autowired private SessionBean sessionBean;

    @GetMapping
    public Map<String, Object> getBeanScopes() {
        Map<String, Object> result = new HashMap<>();

        result.put("singletonBean", format(singletonBean));
        result.put("prototypeBean", format(prototypeBean));
        result.put("requestBean", format(requestBean));
        result.put("sessionBean", format(sessionBean));

        return result;
    }

    private Map<String, String> format(BeanInfo bean) {
        Map<String, String> info = new HashMap<>();
        info.put("id", bean.getId());
        info.put("time", bean.getTime().toString());
        return info;
    }
}
