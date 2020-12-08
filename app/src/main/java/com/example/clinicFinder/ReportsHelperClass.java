package com.example.clinicFinder;

public class ReportsHelperClass {
    String imgurl;
    String namereport;

    public ReportsHelperClass() {
    }
    public ReportsHelperClass( String namereport,String imgurl) {
        this.imgurl = imgurl;
        this.namereport = namereport;
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
}
