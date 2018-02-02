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
    private Long questionId;
    private String option;
    private Boolean isAnswer;

    @Generated
    public Options() {
    }

    public Options(Long id) {
        this.id = id;
    }

    @Generated
    public Options(Long id, Long questionId, String option, Boolean isAnswer) {
        this.id = id;
        this.questionId = questionId;
        this.option = option;
        this.isAnswer = isAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Boolean isAnswer) {
        this.isAnswer = isAnswer;
    }

}