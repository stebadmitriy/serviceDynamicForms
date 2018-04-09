package com.example.serviceDynamicForms.controllers;


import com.example.serviceDynamicForms.model.form.meta.MetaFields;
import com.example.serviceDynamicForms.model.form.meta.MetaForm;
import com.example.serviceDynamicForms.model.form.meta.MetaValues;
import com.example.serviceDynamicForms.model.form.values.Form;
import com.example.serviceDynamicForms.model.result.Result;
import com.example.serviceDynamicForms.service.FormService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/forms/value")
public class FormController {
    @Autowired
    FormService formService;

    private final static String RESULT_SUCCESSFUL = "успешно";
    private final static String RESULT_ERROR = "ошибка";
    private final static String TYPE_LIST = "list";
    private final static String META_FORM_LIST = "metaFormList";

    @RequestMapping(value = "/create", method = POST)
    public Result createValuesForm(@RequestBody @NonNull Form form, HttpSession session) {

        List<MetaForm> metaForms = (List<MetaForm>) session.getAttribute(META_FORM_LIST);
        List<String> formKeys = new ArrayList<>(form.getForm().keySet());
        List<String> formValues = new ArrayList<>(form.getForm().values());
        Result result = new Result();

        if (checkName(metaForms, formKeys) && checkValueType(metaForms, formValues)) {
            result.setResult(RESULT_SUCCESSFUL);
            formService.save(form);
        } else {
            result.setResult(RESULT_ERROR);
        }
        return result;

    }

    private boolean checkName(List<MetaForm> metaForms, List<String> formKeys) {
        boolean isValid = true;
        List<MetaFields> metaFields = metaForms.get(0).getFields();
        List<String> names = new ArrayList<>();

        for (MetaFields meta : metaFields) {
            names.add(meta.getName());
        }

        if (names.size() != formKeys.size() || !names.containsAll(formKeys)) {
            isValid = false;
        }
        return isValid;
    }

    private boolean checkValueType(List<MetaForm> metaForms, List<String> formValues) {
        boolean isValid = true;
        List<MetaFields> metaFields = metaForms.get(0).getFields();
        for (MetaFields meta : metaFields) {
            if (meta.getType().equalsIgnoreCase(TYPE_LIST)) {
                MetaValues metaValue = meta.getValues();
                for (String formValue : formValues) {
                    if (!formValue.equalsIgnoreCase(metaValue.getNone()) &&
                            !formValue.equalsIgnoreCase(metaValue.getV1()) &&
                            !formValue.equalsIgnoreCase(metaValue.getV2()) &&
                            !formValue.equalsIgnoreCase(metaValue.getV3())) {
                        isValid = false;
                    }
                }
            }
        }
        return isValid;
    }
}
