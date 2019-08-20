package com.keshar.mockitotestingexample.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class User  implements Parcelable {
    private String firstname,lastname,country,city;
    private long birthDate;

    public User(String firstname, String lastname, String country, long birthDate,String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.birthDate = birthDate;
        this.city=city;
    }

    protected User(Parcel in) {
        firstname = in.readString();
        lastname = in.readString();
        country = in.readString();
        birthDate = in.readLong();
        city=in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(country);
        dest.writeLong(birthDate);
        dest.writeString(city);
    }
}
