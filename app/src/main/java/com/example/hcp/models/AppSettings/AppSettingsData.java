package com.example.hcp.models.AppSettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppSettingsData {
        @SerializedName("AspNetUser")
        @Expose
        private Object aspNetUser;
        @SerializedName("AppSettingId")
        @Expose
        private Integer appSettingId;
        @SerializedName("Version")
        @Expose
        private String version;
        @SerializedName("EnableFlag")
        @Expose
        private Boolean enableFlag;
        @SerializedName("CreatedBy")
        @Expose
        private String createdBy;
        @SerializedName("CreatedOn")
        @Expose
        private String createdOn;

        public Object getAspNetUser() {
            return aspNetUser;
        }

        public void setAspNetUser(Object aspNetUser) {
            this.aspNetUser = aspNetUser;
        }

        public Integer getAppSettingId() {
            return appSettingId;
        }

        public void setAppSettingId(Integer appSettingId) {
            this.appSettingId = appSettingId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Boolean getEnableFlag() {
            return enableFlag;
        }

        public void setEnableFlag(Boolean enableFlag) {
            this.enableFlag = enableFlag;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }


}
