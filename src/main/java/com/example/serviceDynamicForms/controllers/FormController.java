package com.example.serviceDynamicForms.controllers;

import com.example.serviceDynamicForms.model.form.meta.MetaFields;
import com.example.serviceDynamicForms.model.form.meta.MetaForm;
import com.example.serviceDynamicForms.model.form.values.Form;
import com.example.serviceDynamicForms.model.result.Result;
import com.example.serviceDynamicForms.service.FormService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    public Result createValuesForm(@RequestBody @NonNull Form form, HttpSession session, HttpServletResponse response) throws IOException {
        Result result = new Result();
        List<MetaForm> metaForms = (List<MetaForm>) session.getAttribute(META_FORM_LIST);
        List<String> formKeys = new ArrayList<>(form.getForm().keySet());
        List<String> formValues = new ArrayList<>(form.getForm().values());

        if (checkName(metaForms, formKeys) && checkValueType(metaForms, formValues)) {
            result.setResult(RESULT_SUCCESSFUL);
            formService.save(form);
        } else {
            result.setResult(RESULT_ERROR);
        }
        return result;

    }

    @ExceptionHandler(NullPointerException.class)
    public Result handleNPException(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_REQUEST_TIMEOUT);
        Result result = new Result();
        result.setResult(RESULT_ERROR);
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
        for (int i = 0; i < metaFields.size(); i++) {
            if (metaFields.get(i).getType().equalsIgnoreCase(TYPE_LIST)) {
                List<String> values = new ArrayList<>(metaFields.get(i).getValues().values());
                if (!values.contains(formValues.get(i))) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }
}
