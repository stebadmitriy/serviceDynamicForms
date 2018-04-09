package com.example.serviceDynamicForms.service;

import com.example.serviceDynamicForms.model.form.values.Form;
import com.example.serviceDynamicForms.persistence.FormRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Autowired
    private FormRepository repository;

    public void save(@NonNull Form form) {
        repository.save(form);

    }
}
