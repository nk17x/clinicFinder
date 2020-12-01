package com.example.clinicFinder;

public class gastricpost {
    String doctorname;
    String selectedExperience;

    public gastricpost() {
    }

    @Override
    public String toString() {
        return "gastricpost{" +
                "doctorname='" + doctorname + '\'' +
                ", selectedExperience='" + selectedExperience + '\'' +
                ", selectedSpeciality='" + selectedSpeciality + '\'' +
                '}';
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

    String selectedSpeciality;
}
