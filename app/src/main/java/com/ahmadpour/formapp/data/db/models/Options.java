package com.ahmadpour.formapp.data.db.models;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "OPTIONS".
 */
@Entity
public class Options {

    @Id
    private Long id;
    private Long formId;
    private Long questionId;
    private String option;

    @Generated
    public Options() {
    }

    public Options(Long id) {
        this.id = id;
    }

    @Generated
    public Options(Long id, Long formId, Long questionId, String option) {
        this.id = id;
        this.formId = formId;
        this.questionId = questionId;
        this.option = option;
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}
