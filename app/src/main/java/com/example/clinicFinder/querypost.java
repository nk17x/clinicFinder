package com.example.clinicFinder;

public class querypost {
    String username,qtopic,qproblem,qdescribe,qanswer;

    public querypost() {
    }

    @Override
    public String toString() {
        return "querypost{" +
                "username='" + username + '\'' +
                ", qtopic='" + qtopic + '\'' +
                ", qproblem='" + qproblem + '\'' +
                ", qdescribe='" + qdescribe + '\'' +
                ", qanswer='" + qanswer + '\'' +
                '}';
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
}
