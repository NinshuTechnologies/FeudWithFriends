package com.ninshu.feudWithFriends.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(name = "question")
    private String question;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "modified_time")
    private Timestamp modifiedTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", fetch = FetchType.EAGER)
    private List<AnswerList> allAnswerList;

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

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public List<AnswerList> getAnswerList() {
        return allAnswerList;
    }

    public void setAnswerList(List<AnswerList> allAnswerList) {
        this.allAnswerList = allAnswerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "uid=" + uid +
                ", question='" + question + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", answerList=" + allAnswerList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return uid == question1.uid &&
                Objects.equals(question, question1.question) &&
                Objects.equals(createdTime, question1.createdTime) &&
                Objects.equals(modifiedTime, question1.modifiedTime) &&
                Objects.equals(allAnswerList, question1.allAnswerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, question, createdTime, modifiedTime, allAnswerList);
    }
}
