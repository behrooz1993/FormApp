package com.ahmadpour.databasegenerator;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class Main {

    public static void main(String... args) throws Exception{

        Schema mSchema = new Schema(1,"com.ahmadpour.formapp.data.db.models");

        Entity form = mSchema.addEntity("Forms");
        form.addLongProperty("id").primaryKey();
        form.addStringProperty("title");

        Entity question = mSchema.addEntity("Questions");
        question.addLongProperty("id").primaryKey();
        question.addLongProperty("formId");
        question.addStringProperty("question");
        question.addIntProperty("type");

        Entity option = mSchema.addEntity("Options");
        option.addLongProperty("id").primaryKey();
        option.addLongProperty("formId");
        option.addLongProperty("questionId");
        option.addStringProperty("option");

        Entity answer = mSchema.addEntity("Answers");
        answer.addLongProperty("id").primaryKey();
        answer.addLongProperty("formId");
        answer.addLongProperty("questionId");
        answer.addLongProperty("optionId");
        answer.addStringProperty("answer");
        answer.addIntProperty("temp");
        answer.addStringProperty("date");

        Entity code = mSchema.addEntity("Codes");
        code.addLongProperty("id").primaryKey();
        code.addLongProperty("formId");
        code.addStringProperty("date");
        code.addStringProperty("code");

        new DaoGenerator().generateAll(mSchema, "../app/src/main/java");
    }

}
