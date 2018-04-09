package com.example.serviceDynamicForms.model.form.meta;

import lombok.Data;

import java.io.Serializable;

@Data
public class MetaFields implements Serializable {
    private String title;
    private String name;
    private String type;
    private MetaValues values;

    private final static long serialVersionUID = -1015894459516514675L;


}
