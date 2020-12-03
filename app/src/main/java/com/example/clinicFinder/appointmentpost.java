package com.example.clinicFinder;

public class appointmentpost {
    String doctorname;
    String datetime;
    String imgurl;
    String speciality;

    @Override
    public String toString() {
        return "appointmentpost{" +
                "doctorname='" + doctorname + '\'' +
                ", datetime='" + datetime + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }

    public appointmentpost(String doctorname, String datetime, String imgurl, String speciality) {
        this.doctorname = doctorname;
        this.datetime = datetime;
        this.imgurl = imgurl;
        this.speciality = speciality;
    }

    public appointmentpost() {
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
