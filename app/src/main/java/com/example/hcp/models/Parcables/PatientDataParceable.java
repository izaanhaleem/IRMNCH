package com.example.hcp.models.Parcables;

import android.os.Parcel;
import android.os.Parcelable;

public class PatientDataParceable implements Parcelable {

    public String contactNo, MrNo,PatientName,LeaderCNIC,fingerprint,patientType;
    public int pid,isnewPatient,isvital,isassessment,isvaccination,issample;

    public PatientDataParceable(Parcel in) {
        contactNo = in.readString();
        MrNo = in.readString();
        PatientName = in.readString();
        LeaderCNIC = in.readString();
        fingerprint = in.readString();
        patientType = in.readString();
        pid = in.readInt();
        isnewPatient = in.readInt();
        isvital = in.readInt();
        isassessment = in.readInt();
        isvaccination = in.readInt();
        issample = in.readInt();

    }

    public static final Creator<PatientDataParceable> CREATOR = new Creator<PatientDataParceable>() {
        @Override
        public PatientDataParceable createFromParcel(Parcel in) {
            return new PatientDataParceable(in);
        }

        @Override
        public PatientDataParceable[] newArray(int size) {
            return new PatientDataParceable[size];
        }
    };

    public PatientDataParceable() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(MrNo);
        parcel.writeString(contactNo);
        parcel.writeString(PatientName);
        parcel.writeString(LeaderCNIC);
        parcel.writeString(fingerprint);
        parcel.writeString(patientType);
        parcel.writeInt(pid);
        parcel.writeInt(isnewPatient);
        parcel.writeInt(isvital);
        parcel.writeInt(isassessment);
        parcel.writeInt(isvaccination);
        parcel.writeInt(issample);
    }

}
