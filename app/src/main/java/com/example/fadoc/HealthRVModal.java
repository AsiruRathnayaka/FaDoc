package com.example.fadoc;

import android.os.Parcel;
import android.os.Parcelable;

public class HealthRVModal implements Parcelable {
    private String weight;
    private String height;
    private String ongoing;
    private String allergics;
    private String bmi;
    private String memberID;
    private String membername;

    public HealthRVModal(){

    }

    public HealthRVModal(String membername,String weight, String height, String ongoing, String allergics, String bmi,String memberID) {
        this.weight = weight;
        this.height = height;
        this.ongoing = ongoing;
        this.allergics = allergics;
        this.bmi = bmi;
        this.memberID=memberID;
        this.membername = membername;
    }

    protected HealthRVModal(Parcel in) {
        weight = in.readString();
        height = in.readString();
        ongoing = in.readString();
        allergics = in.readString();
        bmi = in.readString();
        memberID = in.readString();
        membername = in.readString();
    }

    public static final Creator<HealthRVModal> CREATOR = new Creator<HealthRVModal>() {
        @Override
        public HealthRVModal createFromParcel(Parcel in) {
            return new HealthRVModal(in);
        }

        @Override
        public HealthRVModal[] newArray(int size) {
            return new HealthRVModal[size];
        }
    };

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getOngoing() {
        return ongoing;
    }

    public void setOngoing(String ongoing) {
        this.ongoing = ongoing;
    }

    public String getAllergics() {
        return allergics;
    }

    public void setAllergics(String allergics) {
        this.allergics = allergics;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMembername () { return membername; }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(weight);
        parcel.writeString(height);
        parcel.writeString(ongoing);
        parcel.writeString(allergics);
        parcel.writeString(bmi);
        parcel.writeString(memberID);
        parcel.writeString(membername);
    }
}

