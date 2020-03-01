package com.ninshu.feudWithFriends;

import com.ninshu.feudWithFriends.Entities.AnswerList;
import com.ninshu.feudWithFriends.Entities.Question;

import javax.persistence.*;
import java.util.Objects;

public class AnswerListVO {
        private int uid;

        private String createdTime;

        private String modifiedTime;

        private String answerWords;

        private String originalAnswerType;

        private String currentAnswerType;

        private long rank;

        private long hits;

        private String displayAnswer;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
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

    @Override
    public String toString() {
        return "AnswerListVO{" +
                "uid=" + uid +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", answerWords='" + answerWords + '\'' +
                ", originalAnswerType='" + originalAnswerType + '\'' +
                ", currentAnswerType='" + currentAnswerType + '\'' +
                ", rank=" + rank +
                ", hits=" + hits +
                ", displayAnswer='" + displayAnswer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerListVO that = (AnswerListVO) o;
        return uid == that.uid &&
                rank == that.rank &&
                hits == that.hits &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(modifiedTime, that.modifiedTime) &&
                Objects.equals(answerWords, that.answerWords) &&
                Objects.equals(originalAnswerType, that.originalAnswerType) &&
                Objects.equals(currentAnswerType, that.currentAnswerType) &&
                Objects.equals(displayAnswer, that.displayAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, createdTime, modifiedTime, answerWords, originalAnswerType, currentAnswerType, rank, hits, displayAnswer);
    }
}
