package com.example.clinicFinder;

public class AppointmentHelperClass {
    String username;
    String doctorname;
    String dbtime;
    String dbdate;
    String imgurl;
    String speciality;

    public AppointmentHelperClass() {
    }

    public AppointmentHelperClass(String username, String doctorname, String dbtime, String dbdate, String imgurl, String speciality) {
        this.username = username;
        this.doctorname = doctorname;
        this.dbtime = dbtime;
        this.dbdate = dbdate;
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

    public String getDbtime() {
        return dbtime;
    }

    public void setDbtime(String dbtime) {
        this.dbtime = dbtime;
    }

    public String getDbdate() {
        return dbdate;
    }

    public void setDbdate(String dbdate) {
        this.dbdate = dbdate;
    }

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
}
