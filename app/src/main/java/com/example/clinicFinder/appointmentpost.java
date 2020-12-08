package com.example.clinicFinder;

public class appointmentpost {
    String doctorname;
    String dbdate;
    String dbtime;
    String imgurl;
    String speciality;

    public appointmentpost() {
    }

    @Override
    public String toString() {
        return "appointmentpost{" +
                "doctorname='" + doctorname + '\'' +
                ", dbdate='" + dbdate + '\'' +
                ", dbtime='" + dbtime + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDbdate() {
        return dbdate;
    }

    public void setDbdate(String dbdate) {
        this.dbdate = dbdate;
    }

    public String getDbtime() {
        return dbtime;
    }

    public void setDbtime(String dbtime) {
        this.dbtime = dbtime;
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
