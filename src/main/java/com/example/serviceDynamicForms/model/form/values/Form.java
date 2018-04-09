package com.example.serviceDynamicForms.model.form.values;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Form implements Serializable {

    private Map<String, String> form = new LinkedHashMap<>();
    private static final long serialVersionUID = -3779410087254562604L;

}
