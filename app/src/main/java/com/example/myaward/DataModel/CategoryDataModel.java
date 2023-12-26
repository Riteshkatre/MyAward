package com.example.myaward.DataModel;

import java.util.List;

public class CategoryDataModel {

    String categoryName;
    List<ScoreDataModel> scoreDataModels;

    public CategoryDataModel(String categoryName, List<ScoreDataModel> scoreDataModels) {
        this.categoryName = categoryName;
        this.scoreDataModels = scoreDataModels;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ScoreDataModel> getScoreDataModels() {
        return scoreDataModels;
    }

    public void setScoreDataModels(List<ScoreDataModel> scoreDataModels) {
        this.scoreDataModels = scoreDataModels;
    }

    public static class ScoreDataModel {

        String name;
        String email;
        String percentage;
        String address;
        String certificate;

        String toppername;
        String mobileno;
        String relation;

        String rankno;

        String status;

        public ScoreDataModel(String name, String email, String percentage, String address, String certificate, String toppername, String mobileno, String relation, String rankno, String status) {
            this.name = name;
            this.email = email;
            this.percentage = percentage;
            this.address = address;
            this.certificate = certificate;
            this.toppername = toppername;
            this.mobileno = mobileno;
            this.relation = relation;
            this.rankno = rankno;
            this.status = status;
        }

        public String getToppername() {
            return toppername;
        }

        public void setToppername(String toppername) {
            this.toppername = toppername;
        }

        public String getMobileno() {
            return mobileno;
        }

        public void setMobileno(String mobileno) {
            this.mobileno = mobileno;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public String getRankno() {
            return rankno;
        }

        public void setRankno(String rankno) {
            this.rankno = rankno;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPercentage() {
            return percentage;
        }

        public void setPercentage(String percentage) {
            this.percentage = percentage;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }
    }

}
