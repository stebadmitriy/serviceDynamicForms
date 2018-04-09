package com.example.serviceDynamicForms.persistence;

import com.example.serviceDynamicForms.model.form.values.Form;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends MongoRepository<Form, String> {
}
