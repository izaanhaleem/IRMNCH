package com.example.hcp.models.Parcables;

import android.os.Parcel;
import android.os.Parcelable;

public class pendingDataParceable implements Parcelable {

    public String Gender, MrNo,PatientName,LastName,LeaderCNIC,patientType,pathentContactNo,text1,text2,is_cirrhotic_patient;
    public int pid,hcvviralount,hbvviralcount,sample_id,hcv_medicine_duration;

    public pendingDataParceable(Parcel in) {
        Gender = in.readString();
        MrNo = in.readString();
        PatientName = in.readString();
        LeaderCNIC = in.readString();
        patientType = in.readString();
        pid = in.readInt();
        pathentContactNo = in.readString();
        LastName = in.readString();
        text1 = in.readString();
        text2 = in.readString();
        hcvviralount = in.readInt();
        hcvviralount = in.readInt();
        hcv_medicine_duration = in.readInt();
        sample_id = in.readInt();
        is_cirrhotic_patient = in.readString();
    }

    public static final Creator<pendingDataParceable> CREATOR = new Creator<pendingDataParceable>() {
        @Override
        public pendingDataParceable createFromParcel(Parcel in) {
            return new pendingDataParceable(in);
        }

        @Override
        public pendingDataParceable[] newArray(int size) {
            return new pendingDataParceable[size];
        }
    };

    public pendingDataParceable() {

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
        parcel.writeString(text1);
        parcel.writeString(text2);
        parcel.writeInt(hcvviralount);
        parcel.writeInt(hbvviralcount);
        parcel.writeInt(sample_id);
        parcel.writeInt(hcv_medicine_duration);
        parcel.writeString(is_cirrhotic_patient);

    }


}
