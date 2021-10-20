package com.example.hcp.models.AdaptersData;

public class SearchResultData {

    public String contactNo, MrNo,PatientName,LeaderCNIC,fingerprint,patienttype;
    public int pid,isnewpatient,isvital,isassessment,isvaccination,issample;


    public SearchResultData(String contactNo, String mrNo, String patientName, String leaderCNIC, String fingerPrint, int PID,String patientType,int isNewPatient,int isVital,int isAssessment,int isVaccination,int isSample) {
        this.contactNo = contactNo;
        MrNo = mrNo;
        PatientName = patientName;
        LeaderCNIC = leaderCNIC;
        fingerprint = fingerPrint;
        patienttype = patientType;
        pid = PID;
        isnewpatient = isNewPatient;
        isvital = isVital;
        isassessment = isAssessment;
        isvaccination = isVaccination;
        issample = isSample;

    }

    public SearchResultData() {

    }
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public String getPatienttype() {
        return patienttype;
    }

    public void setPatienttype(String patienttype) {
        this.patienttype = patienttype;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public int getIsnewpatient() {
        return isnewpatient;
    }

    public void setIsnewpatient(int isnewpatient) {
        this.isnewpatient = isnewpatient;
    }


    public int getIsvital() {
        return isvital;
    }

    public void setIsvital(int isvital) {
        this.isvital = isvital;
    }


    public int getIsassessment() {
        return isassessment;
    }

    public void setIsassessment(int isassessment) {
        this.isassessment = isassessment;
    }

    public int getIsvaccination() {
        return isvaccination;
    }

    public void setIsvaccination(int isvaccination) {
        this.isvaccination = isvaccination;
    }

    public int getIssample() {
        return issample;
    }

    public void setIssample(int issample) {
        this.issample = issample;
    }
}

