package com.ninshu.feudWithFriends.model;

import com.ninshu.feudWithFriends.Entities.AnswerList;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class QuestionVO {

    private int uid;

    private String question;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionVO{" +
                "uid=" + uid +
                ", question='" + question + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionVO that = (QuestionVO) o;
        return uid == that.uid &&
                Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, question);
    }
}
