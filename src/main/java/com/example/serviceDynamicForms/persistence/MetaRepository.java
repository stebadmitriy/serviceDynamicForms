package com.example.serviceDynamicForms.persistence;

import com.example.serviceDynamicForms.model.form.meta.MetaForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends MongoRepository<MetaForm, String> {
}
