package com.ninshu.feudWithFriends.Entities;

import javax.persistence.*;

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

    @Column(name = "question_reference_id")
    private long questionReferenceId;


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


    public long getQuestionReferenceId() {
        return questionReferenceId;
    }

    public void setQuestionReferenceId(long questionReferenceId) {
        this.questionReferenceId = questionReferenceId;
    }

}
