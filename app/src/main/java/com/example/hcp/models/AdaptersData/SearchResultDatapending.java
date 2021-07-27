package com.example.hcp.models.AdaptersData;

public class SearchResultDatapending {

    public String Gneder, MrNo,PatientName,LeaderCNIC,patienttype,Text1,Text2,Is_cirrhotic_patient;
    public int Pid,Hcvviralcount,Hbvviralcount,Sample_id,Hcv_medicine_duration;

    public SearchResultDatapending(String gneder, String mrNo, String patientName, String leaderCNIC, String patientType, int pid,String  text1,String  text2,int hcvviralcount,int hbvviralcount,int sample_id,String is_cirrhotic_patient,int hcv_medicine_duration) {
        Gneder = gneder;
        MrNo = mrNo;
        PatientName = patientName;
        LeaderCNIC = leaderCNIC;
        patienttype = patientType;
        Pid = pid;
        Text1 = text1;
        Text2 = text2;
        Hcvviralcount = hcvviralcount;
        Hbvviralcount = hbvviralcount;
        Sample_id = sample_id;
        Is_cirrhotic_patient = is_cirrhotic_patient;
        Hcv_medicine_duration = hcv_medicine_duration;
    }

    public int getHcv_medicine_duration() {
        return Hcv_medicine_duration;
    }

    public void setHcv_medicine_duration(int hcv_medicine_duration) {
        Hcv_medicine_duration = hcv_medicine_duration;
    }

    public SearchResultDatapending() {

    }
    public String getText1() {
        return Text1;
    }

    public void setText1(String text1) {
        Text1 = text1;
    }

    public String getText2() {
        return Text2;
    }

    public void setText2(String text2) {
        Text2 = text2;
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

    public int getHcvviralcount() {
        return Hcvviralcount;
    }

    public void setHcvviralcount(int hcvviralcount) {
        Hcvviralcount = hcvviralcount;
    }

    public int getHbvviralcount() {
        return Hbvviralcount;
    }

    public void setHbvviralcount(int hbvviralcount) {
        Hbvviralcount = hbvviralcount;
    }

    public String getIs_cirrhotic_patient() {
        return Is_cirrhotic_patient;
    }

    public void setIs_cirrhotic_patient(String is_cirrhotic_patient) {
        Is_cirrhotic_patient = is_cirrhotic_patient;
    }

    public int getSample_id() {
        return Sample_id;
    }

    public void setSample_id(int sample_id) {
        Sample_id = sample_id;
    }
}
