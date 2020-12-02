package com.example.clinicFinder;

public class DoctorHelperClass {
    String selectedExperience;
    String doctorname;
    String selectedSpeciality;
    String imgurl;


    public String getimgurl() {
        return imgurl;
    }

    public void setimgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getSelectedExperience() {
        return selectedExperience;
    }

    public void setSelectedExperience(String selectedExperience) {
        this.selectedExperience = selectedExperience;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getSelectedSpeciality() {
        return selectedSpeciality;
    }

    public void setSelectedSpeciality(String selectedSpeciality) {
        this.selectedSpeciality = selectedSpeciality;
    }


    public DoctorHelperClass(String doctorname, String selectedSpeciality, String selectedExperience,String imgurl) {
        this.selectedExperience = selectedExperience;
        this.doctorname = doctorname;
        this.selectedSpeciality = selectedSpeciality;
        this.imgurl = imgurl;
    }
}
