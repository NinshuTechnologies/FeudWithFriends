package com.ninshu.feudWithFriends.model;

import java.util.Objects;

public class AnswerListVO {
        private int uid;

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
                Objects.equals(answerWords, that.answerWords) &&
                Objects.equals(originalAnswerType, that.originalAnswerType) &&
                Objects.equals(currentAnswerType, that.currentAnswerType) &&
                Objects.equals(displayAnswer, that.displayAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid,answerWords, originalAnswerType, currentAnswerType, rank, hits, displayAnswer);
    }
}
