package com.example.clinicFinder;

public class AppointmentHelperClass {
    String username;
    String doctorname;
    String datetime;
    String imgurl;
    String speciality;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public AppointmentHelperClass(String username, String doctorname, String datetime, String imgurl, String speciality) {
        this.username = username;
        this.doctorname = doctorname;
        this.datetime = datetime;
        this.imgurl = imgurl;
        this.speciality = speciality;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
