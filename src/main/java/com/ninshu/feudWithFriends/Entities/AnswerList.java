package com.ninshu.feudWithFriends.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "answer_list")
public class AnswerList {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "modified_time")
    private String modifiedTime;

    @Column(name = "answer_words")
    private String answerWords;

    @Column(name = "original_answer_type")
    private String originalAnswerType;

    @Column(name = "current_answer_type")
    private String currentAnswerType;

    @Column(name = "rank")
    private long rank;

    @Column(name = "hits")
    private long hits;

    @Column(name = "display_answer")
    private String displayAnswer;

    @ManyToOne
    @JoinColumn(name = "question_reference_id")
    private Question question;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getCreationTime() {
        return createdTime;
    }

    public void setCreationTime(String creationTime) {
        this.createdTime = createdTime;
    }


    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }


    public String getAnswerWords() {
        return answerWords;
    }

    public void setAnswerWords(String answerWords) {
        this.answerWords = answerWords;
    }


    public String getOriginalAnswerType() {
        return originalAnswerType;
    }

    public void setOriginalAnswerType(String originalAnswerType) {
        this.originalAnswerType = originalAnswerType;
    }


    public String getCurrentAnswerType() {
        return currentAnswerType;
    }

    public void setCurrentAnswerType(String currentAnswerType) {
        this.currentAnswerType = currentAnswerType;
    }


    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }


    public long getHits() {
        return hits;
    }

    public void setHits(long hits) {
        this.hits = hits;
    }


    public String getDisplayAnswer() {
        return displayAnswer;
    }

    public void setDisplayAnswer(String displayAnswer) {
        this.displayAnswer = displayAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "AnswerList{" +
                "uid=" + uid +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", answerWords='" + answerWords + '\'' +
                ", originalAnswerType='" + originalAnswerType + '\'' +
                ", currentAnswerType='" + currentAnswerType + '\'' +
                ", rank=" + rank +
                ", hits=" + hits +
                ", displayAnswer='" + displayAnswer + '\'' +
                ", question=" + question +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerList that = (AnswerList) o;
        return uid == that.uid &&
                rank == that.rank &&
                hits == that.hits &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedTime, that.modifiedTime) &&
                Objects.equals(answerWords, that.answerWords) &&
                Objects.equals(originalAnswerType, that.originalAnswerType) &&
                Objects.equals(currentAnswerType, that.currentAnswerType) &&
                Objects.equals(displayAnswer, that.displayAnswer) &&
                Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, createdTime, modifiedTime, answerWords, originalAnswerType, currentAnswerType, rank, hits, displayAnswer, question);
    }
}
