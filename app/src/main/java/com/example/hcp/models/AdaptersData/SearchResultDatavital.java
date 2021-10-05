package com.example.hcp.models.AdaptersData;

public class SearchResultDatavital {

    public String Gneder, MrNo,PatientName,LeaderCNIC,patienttype,sample_staus,sample_number,hcvviralload,hbvviralload,transferin,currnet_hospital,ex_hospital;
    public int Pid;

    public SearchResultDatavital(String gneder, String mrNo, String patientName, String leaderCNIC, String patientType,int pid,String Sample_status,String Sample_number,String Hcvviralload,String Hbvviralload,String TransferIn,String Current_Hospital,String Ex_Hospital) {
        Gneder = gneder;
        MrNo = mrNo;
        PatientName = patientName;
        LeaderCNIC = leaderCNIC;
        patienttype = patientType;
        Pid = pid;
        sample_staus = Sample_status;
        sample_number = Sample_number;
        hcvviralload = Hcvviralload;
        hbvviralload = Hbvviralload;
        transferin  = TransferIn;
        currnet_hospital = Current_Hospital;
        ex_hospital = Ex_Hospital;
    }

    public SearchResultDatavital() {

    }


    public String getSample_staus() {
        return sample_staus;
    }

    public void setSample_staus(String sample_staus) {
        this.sample_staus = sample_staus;
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

    public String getSample_number() {
        return sample_number;
    }

    public void setSample_number(String sample_number) {
        this.sample_number = sample_number;
    }


    public String getHcvviralload() {
        return hcvviralload;
    }

    public void setHcvviralload(String hcvviralload) {
        this.hcvviralload = hcvviralload;
    }

    public String getHbvviralload() {
        return hbvviralload;
    }

    public void setHbvviralload(String hbvviralload) {
        this.hbvviralload = hbvviralload;
    }


    public String getTransferin() {
        return transferin;
    }

    public void setTransferin(String transferin) {
        this.transferin = transferin;
    }

    public String getCurrnet_hospital() {
        return currnet_hospital;
    }

    public void setCurrnet_hospital(String currnet_hospital) {
        this.currnet_hospital = currnet_hospital;
    }

    public String getEx_hospital() {
        return ex_hospital;
    }

    public void setEx_hospital(String ex_hospital) {
        this.ex_hospital = ex_hospital;
    }
}
