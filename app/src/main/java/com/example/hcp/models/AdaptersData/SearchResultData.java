package com.example.hcp.models.AdaptersData;

public class SearchResultData {

    public String contactNo, MrNo,PatientName,LeaderCNIC;

    public SearchResultData(String contactNo, String mrNo, String patientName, String leaderCNIC) {
        this.contactNo = contactNo;
        MrNo = mrNo;
        PatientName = patientName;
        LeaderCNIC = leaderCNIC;
    }

    public SearchResultData() {

    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getMrNo() {
        return MrNo;
    }

    public void setMrNo(String mrNo) {
        MrNo = mrNo;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getLeaderCNIC() {
        return LeaderCNIC;
    }

    public void setLeaderCNIC(String leaderCNIC) {
        LeaderCNIC = leaderCNIC;
    }
}
