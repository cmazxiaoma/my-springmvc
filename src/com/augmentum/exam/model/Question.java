package com.augmentum.exam.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.augmentum.exam.utils.FullDate2StringSerializer;
import com.augmentum.exam.utils.String2FullDateSerializer;

public class Question {
    private String questionId;
    private String questionCodeId;
    private String questionDesc;
    private String questionAChoose;
    private String questionBChoose;
    private String questionCChoose;
    private String questionDChoose;
    private String answer;
    private Date createTime;
    private Date updateTime;

    @JsonSerialize(using = FullDate2StringSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    @JsonDeserialize(using = String2FullDateSerializer.class)
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonSerialize(using = FullDate2StringSerializer.class)
    public Date getUpdateTime() {
        return updateTime;
    }

    @JsonDeserialize(using = String2FullDateSerializer.class)
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getQuestionCodeId() {
        return questionCodeId;
    }

    public void setQuestionCodeId(String questionCodeId) {
        this.questionCodeId = questionCodeId;
    }

    public Question() {
        super();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Question(String questionId, String questionCodeId, String questionDesc, String questionAChoose, String questionBCoose, String questionCChoose,String questionDChoose, String answer) {
        super();
        this.questionId = questionId;
        this.questionCodeId = questionCodeId;
        this.questionDesc = questionDesc;
        this.questionAChoose = questionAChoose;
        this.questionBChoose = questionBCoose;
        this.questionCChoose = questionCChoose;
        this.questionDChoose = questionDChoose;
        this.answer = answer;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getQuestionAChoose() {
        return questionAChoose;
    }

    public void setQuestionAChoose(String questionAChoose) {
        this.questionAChoose = questionAChoose;
    }

    public String getQuestionBChoose() {
        return questionBChoose;
    }

    public void setQuestionBChoose(String questionBChoose) {
        this.questionBChoose = questionBChoose;
    }

    public String getQuestionCChoose() {
        return questionCChoose;
    }

    public void setQuestionCChoose(String questionCChoose) {
        this.questionCChoose = questionCChoose;
    }

    public String getQuestionDChoose() {
        return questionDChoose;
    }

    public void setQuestionDChoose(String questionDChoose) {
        this.questionDChoose = questionDChoose;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", questionCodeId=" + questionCodeId + ", questionDesc="
                + questionDesc + ", questionAChoose=" + questionAChoose + ", questionBCoose=" + questionBChoose
                + ", questionCChoose=" + questionCChoose + ", questionDChoose=" + questionDChoose + ", answer="
                + answer + "]";
    }
}
