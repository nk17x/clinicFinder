package com.example.clinicFinder;

public class gastricpost {
    String doctorname;
    String selectedExperience;
    String selectedSpeciality;
    String imgurl;

    @Override
    public String toString() {
        return "gastricpost{" +
                "doctorname='" + doctorname + '\'' +
                ", selectedExperience='" + selectedExperience + '\'' +
                ", selectedSpeciality='" + selectedSpeciality + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }

    public gastricpost() {
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public gastricpost(String doctorname, String selectedExperience, String selectedSpeciality, String imgurl) {
        this.doctorname = doctorname;
        this.selectedExperience = selectedExperience;
        this.selectedSpeciality = selectedSpeciality;
        this.imgurl = imgurl;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getSelectedExperience() {
        return selectedExperience;
    }

    public void setSelectedExperience(String selectedExperience) {
        this.selectedExperience = selectedExperience;
    }

    public String getSelectedSpeciality() {
        return selectedSpeciality;
    }

    public void setSelectedSpeciality(String selectedSpeciality) {
        this.selectedSpeciality = selectedSpeciality;
    }


}
