package com.example.hcp.models.Parcables;

import android.os.Parcel;
import android.os.Parcelable;

public class vitalDataParceable implements Parcelable {

    public String Gender, MrNo,PatientName,LastName,LeaderCNIC,patientType,pathentContactNo,sample_stauts,sample_number,hcvviralLoad,hbvviralLoad,transferstatus,currenthospitalname,exhospitalname;
    public int pid;

    public vitalDataParceable(Parcel in) {
        Gender = in.readString();
        MrNo = in.readString();
        PatientName = in.readString();
        LeaderCNIC = in.readString();
        patientType = in.readString();
        pid = in.readInt();
        pathentContactNo = in.readString();
        LastName = in.readString();
        sample_stauts = in.readString();
        sample_number = in.readString();
        hcvviralLoad = in.readString();
        hbvviralLoad = in.readString();
        transferstatus = in.readString();
        currenthospitalname = in.readString();
        exhospitalname = in.readString();

    }

    public static final Creator<vitalDataParceable> CREATOR = new Creator<vitalDataParceable>() {
        @Override
        public vitalDataParceable createFromParcel(Parcel in) {
            return new vitalDataParceable(in);
        }

        @Override
        public vitalDataParceable[] newArray(int size) {
            return new vitalDataParceable[size];
        }
    };

    public vitalDataParceable() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(MrNo);
        parcel.writeString(Gender);
        parcel.writeString(PatientName);
        parcel.writeString(LeaderCNIC);
        parcel.writeString(patientType);
        parcel.writeInt(pid);
        parcel.writeString(pathentContactNo);
        parcel.writeString(LastName);
        parcel.writeString(sample_stauts);
        parcel.writeString(sample_number);
        parcel.writeString(hcvviralLoad);
        parcel.writeString(hbvviralLoad);
        parcel.writeString(transferstatus);
        parcel.writeString(currenthospitalname);
        parcel.writeString(exhospitalname);

    }


}
