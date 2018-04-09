package com.example.serviceDynamicForms.service;

import com.example.serviceDynamicForms.model.form.meta.MetaForm;
import com.example.serviceDynamicForms.persistence.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaService {
    @Autowired
    private MetaRepository repository;

    public List<MetaForm> findAll() {
        return repository.findAll();
    }
}
