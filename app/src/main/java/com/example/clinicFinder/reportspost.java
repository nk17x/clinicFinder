package com.example.clinicFinder;

public class reportspost {
    String imgurl;
    String namereport;

    @Override
    public String toString() {
        return "reportspost{" +
                "imgurl='" + imgurl + '\'' +
                ", namereport='" + namereport + '\'' +
                '}';
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getNamereport() {
        return namereport;
    }

    public void setNamereport(String namereport) {
        this.namereport = namereport;
    }

    public reportspost() {
    }
}
