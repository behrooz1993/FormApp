package com.ahmadpour.formapp.data.db.models;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "CODES".
 */
@Entity
public class Codes {

    @Id
    private Long id;
    private Long formId;
    private String date;
    private String code;
    private Forms form;

    @Generated
    public Codes() {
    }

    public Codes(Long id) {
        this.id = id;
    }

    @Generated
    public Codes(Long id, Long formId, String date, String code) {
        this.id = id;
        this.formId = formId;
        this.date = date;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Forms getForm() {
        return form;
    }

    public void setForm(Forms form) {
        this.form = form;
    }
}