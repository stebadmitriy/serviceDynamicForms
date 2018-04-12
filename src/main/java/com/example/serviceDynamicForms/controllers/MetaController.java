package com.example.serviceDynamicForms.controllers;

import com.example.serviceDynamicForms.model.form.meta.MetaForm;
import com.example.serviceDynamicForms.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/forms/meta")
public class MetaController {
    @Autowired
    private MetaService metaService;
    private final static String META_FORM_LIST = "metaFormList";


    @RequestMapping(value = "/getAll", method = GET)
    public List<MetaForm> getAllMetaForm(HttpSession session) {
        List<MetaForm> metaForms = metaService.findAll();
        session.setAttribute(META_FORM_LIST, metaForms);
        return metaForms;
    }
}
