package com.example.hcp.models.AdaptersData;

public class GetMemberResultData {

    private String Name, Age ,Gender, CNIC, MaritalStatus, RelationWithLeader , MemberId , MrNo, contact_no,remakrs,dob,education,profession;
    private int FamilyID;


    public GetMemberResultData() {
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getRemakrs() {
        return remakrs;
    }

    public void setRemakrs(String remakrs) {
        this.remakrs = remakrs;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }

    public String getRelationWithLeader() {
        return RelationWithLeader;
    }

    public void setRelationWithLeader(String relationWithLeader) {
        RelationWithLeader = relationWithLeader;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public int getFamilyID() {
        return FamilyID;
    }

    public void setFamilyID(int familyID) {
        FamilyID = familyID;
    }

    public String getMrNo() {
        return MrNo;
    }

    public void setMrNo(String mrNo) {
        MrNo = mrNo;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}
