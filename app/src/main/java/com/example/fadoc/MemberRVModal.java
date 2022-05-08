package com.example.fadoc;

import android.os.Parcel;
import android.os.Parcelable;

public class MemberRVModal implements Parcelable {
    private String Name;
    private String Relation;
    private String age;
    private String gender;
    private String HandI;
    private String memberID;
    private int Dob;


    protected MemberRVModal(Parcel in) {
        Name = in.readString();
        Relation = in.readString();
        age = in.readString();
        gender = in.readString();
        HandI = in.readString();
    }

    public static final Creator<MemberRVModal> CREATOR = new Creator<MemberRVModal>() {
        @Override
        public MemberRVModal createFromParcel(Parcel in) {
            return new MemberRVModal(in);
        }

        @Override
        public MemberRVModal[] newArray(int size) {
            return new MemberRVModal[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String relation) {
        Relation = relation;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHandI() {
        return HandI;
    }

    public void setHandI(String handI) {
        HandI = handI;
    }
    public int getDOB() {
        return Dob;
    }

    public void setDob(){
        this.Dob = Dob;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public MemberRVModal(String name, String relation, String age, String gender, String handI) {
        Name = name;
        Relation = relation;
        this.age = age;
        this.gender = gender;
        HandI = handI;
    }

    public MemberRVModal(String name, String relation, String DOB, String age, String gender, String handi, String memberID){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Name);
        parcel.writeString(Relation);
        parcel.writeString(age);
        parcel.writeString(gender);
        parcel.writeString(HandI);
    }



}
