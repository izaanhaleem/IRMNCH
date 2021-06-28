package com.example.hcp.models.AdaptersData;

public class SearchResultDatavital {

    public String Gneder, MrNo,PatientName,LeaderCNIC,patienttype;
    public int Pid;

    public SearchResultDatavital(String gneder, String mrNo, String patientName, String leaderCNIC, String patientType,int pid) {
        Gneder = gneder;
        MrNo = mrNo;
        PatientName = patientName;
        LeaderCNIC = leaderCNIC;
        patienttype = patientType;
        Pid = pid;
    }

    public SearchResultDatavital() {

    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public String getGneder() {
        return Gneder;
    }

    public void setGneder(String gneder) {
        Gneder = gneder;
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

    public String getPatienttype() {
        return patienttype;
    }

    public void setPatienttype(String patienttype) {
        this.patienttype = patienttype;
    }
}
