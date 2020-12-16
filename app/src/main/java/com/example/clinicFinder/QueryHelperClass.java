package com.example.clinicFinder;

public class QueryHelperClass {
    String username,qtopic,qproblem,qdescribe;

    public QueryHelperClass() {
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

    public QueryHelperClass(String username, String qtopic, String qproblem, String qdescribe) {
        this.username = username;
        this.qtopic = qtopic;
        this.qproblem = qproblem;
        this.qdescribe = qdescribe;
    }
}
