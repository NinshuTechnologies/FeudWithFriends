package com.ninshu.feudWithFriends.model;

import java.util.Objects;

import javax.persistence.Column;

public class ExperienceAndCoinDataVO {
	private boolean isTransactionSuccessFull;
	
    private String currentExperienceValue;

    private String currentCoinValue;

    private String currentUserLevel;

    private String userReferenceId;
    
    private String userErrorMessage;

	public String getUserErrorMessage() {
		return userErrorMessage;
	}

	public void setUserErrorMessage(String userErrorMessage) {
		this.userErrorMessage = userErrorMessage;
	}

	public boolean isTransactionSuccessFull() {
		return isTransactionSuccessFull;
	}

	public void setTransactionSuccessFull(boolean isTransactionSuccessFull) {
		this.isTransactionSuccessFull = isTransactionSuccessFull;
	}

	public String getCurrentExperienceValue() {
		return currentExperienceValue;
	}

	public void setCurrentExperienceValue(String currentExperienceValue) {
		this.currentExperienceValue = currentExperienceValue;
	}

	public String getCurrentCoinValue() {
		return currentCoinValue;
	}

	public void setCurrentCoinValue(String currentCoinValue) {
		this.currentCoinValue = currentCoinValue;
	}

	public String getCurrentUserLevel() {
		return currentUserLevel;
	}

	public void setCurrentUserLevel(String currentUserLevel) {
		this.currentUserLevel = currentUserLevel;
	}

	public String getUserReferenceId() {
		return userReferenceId;
	}

	public void setUserReferenceId(String userReferenceId) {
		this.userReferenceId = userReferenceId;
	}
    
	 @Override
	 public String toString() {
	  return "ExperienceAndCoinDataResult{" +
	                "isTransactionSuccessFull=" + isTransactionSuccessFull +
	                ", currentExperienceValue='" + currentExperienceValue + '\'' +
	                ", currentCoinValue='" + currentCoinValue + '\'' +
	                ", currentUserLevel='" + currentUserLevel + '\'' +
	                ", userReferenceId=" + userReferenceId + '\'' +
	                ", userErrorMessage=" + userErrorMessage +
	                '}';
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ExperienceAndCoinDataVO that = (ExperienceAndCoinDataVO) o;
	        return isTransactionSuccessFull == that.isTransactionSuccessFull &&
	        		userReferenceId == that.userReferenceId &&
	                Objects.equals(currentExperienceValue, that.currentExperienceValue) &&
	                Objects.equals(currentCoinValue, that.currentCoinValue) &&
	                Objects.equals(currentUserLevel, that.currentUserLevel) &&
	                Objects.deepEquals(userErrorMessage, that.userErrorMessage);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(isTransactionSuccessFull,userReferenceId, currentExperienceValue, currentCoinValue, currentUserLevel,userErrorMessage);
	    }

}
