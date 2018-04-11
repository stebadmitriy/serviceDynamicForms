package com.example.serviceDynamicForms.model.form.meta;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class MetaFields implements Serializable {
    private String title;
    private String name;
    private String type;
    private Map<String, String> values = new HashMap<>();
    private final static long serialVersionUID = -1015894459516514675L;


}
