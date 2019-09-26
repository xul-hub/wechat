package com.mybatis.bdtj.login.entity;

public class Question {
    // 安全問題ID
    private int qid;
    // 安全問題字面
    private String content;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "[" + qid + ", " + content + "]";
    }

}
