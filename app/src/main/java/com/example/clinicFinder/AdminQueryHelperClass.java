package com.example.clinicFinder;

public class AdminQueryHelperClass {
    String username,qtopic,qproblem,qdescribe,qanswer;

    public AdminQueryHelperClass() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQtopic() {
        return qtopic;
    }

    public void setQtopic(String qtopic) {
        this.qtopic = qtopic;
    }

    public String getQproblem() {
        return qproblem;
    }

    public void setQproblem(String qproblem) {
        this.qproblem = qproblem;
    }

    public String getQdescribe() {
        return qdescribe;
    }

    public void setQdescribe(String qdescribe) {
        this.qdescribe = qdescribe;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
        this.qanswer = qanswer;
    }

    public AdminQueryHelperClass(String username, String qtopic, String qproblem, String qdescribe, String qanswer) {
        this.username = username;
        this.qtopic = qtopic;
        this.qproblem = qproblem;
        this.qdescribe = qdescribe;
        this.qanswer = qanswer;
    }
}
