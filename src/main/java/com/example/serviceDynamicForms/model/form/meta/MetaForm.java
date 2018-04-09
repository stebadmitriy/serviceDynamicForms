package com.example.serviceDynamicForms.model.form.meta;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class MetaForm implements Serializable {
    private String title;
    private List<MetaFields> fields = new ArrayList<>();
    private final static long serialVersionUID = -4349541646833554298L;


}
