package com.example.hcp.models.Parcables;

import android.os.Parcel;
import android.os.Parcelable;

public class FamilyDataParceable implements Parcelable {

    public String FamilyId, Address,MrNo,LeaderId,LeaderName,LeaderCNIC;

    public FamilyDataParceable(Parcel in) {
        FamilyId = in.readString();
        Address = in.readString();
        MrNo = in.readString();
        LeaderId = in.readString();
        LeaderName = in.readString();
        LeaderCNIC = in.readString();
    }

    public static final Creator<FamilyDataParceable> CREATOR = new Creator<FamilyDataParceable>() {
        @Override
        public FamilyDataParceable createFromParcel(Parcel in) {
            return new FamilyDataParceable(in);
        }

        @Override
        public FamilyDataParceable[] newArray(int size) {
            return new FamilyDataParceable[size];
        }
    };

    public FamilyDataParceable() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(FamilyId);
        parcel.writeString(Address);
        parcel.writeString(MrNo);
        parcel.writeString(LeaderId);
        parcel.writeString(LeaderName);
        parcel.writeString(LeaderCNIC);
    }


}
