package com.ahmadpour.formapp.data.db.models;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "ANSWERS".
 */
@Entity
public class Answers {

    @Id
    private Long id;
    private Long formId;
    private Long questionId;
    private Long optionId;
    private String answer;
    private Integer temp;
    private String date;
    private Questions question;

    @Generated
    public Answers() {
    }

    public Answers(Long id) {
        this.id = id;
    }

    @Generated
    public Answers(Long id, Long formId, Long questionId, Long optionId, String answer, Integer temp, String date) {
        this.id = id;
        this.formId = formId;
        this.questionId = questionId;
        this.optionId = optionId;
        this.answer = answer;
        this.temp = temp;
        this.date = date;
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

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }
}
