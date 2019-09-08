package com.javaphite.bingo.regression;

public class Report  implements Comparable<Report> {
    private String category;

    private String expression;

    private Double criteria;

    @Override
    public int compareTo(Report report) {
        if(null == report) {
            return -1;
        }
        return Double.compare(criteria, report.criteria);
    }

    // TODO: add equals and hashcode

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Double getCriteria() {
        return criteria;
    }

    public void setCriteria(Double criteria) {
        this.criteria = criteria;
    }
}
