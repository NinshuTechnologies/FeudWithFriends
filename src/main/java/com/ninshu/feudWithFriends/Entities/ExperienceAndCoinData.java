package com.ninshu.feudWithFriends.Entities;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "experience_coin")
public class ExperienceAndCoinData {
	 	@Id
	    @Column(name = "uid")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int uid;

	    @Column(name = "created_time", insertable=false)
	    private String createdTime;

	    @Column(name = "modified_time", insertable=false)
	    private String modifiedTime;

	    @Column(name = "current_experience_value")
	    private String currentExperienceValue;

	    @Column(name = "current_coin_value")
	    private String currentCoinValue;

	    @Column(name = "current_user_level")
	    private String currentUserLevel;

	    @Column(name = "user_reference_id")
	    private String userReferenceId;

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
	        return "ExperienceAndCoinData{" +
	                "uid=" + uid +
	                ", createdTime='" + createdTime + '\'' +
	                ", modifiedTime='" + modifiedTime + '\'' +
	                ", currentExperienceValue='" + currentExperienceValue + '\'' +
	                ", currentCoinValue='" + currentCoinValue + '\'' +
	                ", currentUserLevel='" + currentUserLevel + '\'' +
	                ", userReferenceId=" + userReferenceId +
	                '}';
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ExperienceAndCoinData that = (ExperienceAndCoinData) o;
	        return uid == that.uid &&
	                Objects.equals(createdTime, that.createdTime) &&
	                Objects.equals(modifiedTime, that.modifiedTime) &&
	                Objects.equals(currentExperienceValue, that.currentExperienceValue) &&
	                Objects.equals(currentCoinValue, that.currentCoinValue) &&
	                Objects.equals(currentUserLevel, that.currentUserLevel) &&
	                Objects.equals(userReferenceId, that.userReferenceId);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(uid, createdTime, modifiedTime, currentExperienceValue, currentCoinValue, currentUserLevel, userReferenceId);
	    }

}
