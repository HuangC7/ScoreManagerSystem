package com.hnchances.hyx.aliyun;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * author: yuexuan_huang@126.com
 * Date: 2022/8/2
 * Description:短信工具类 配置类
 */

@ConfigurationProperties(prefix = "sms.aliyun")
@Component
public class SmsProerties {

    private  String regionId;
    private  String accessKeyId;
    private  String accessSecret;
    private  String templateCode;
    private  String signName;
    private  String messageType;
    private  String queueName;


    @PostConstruct
    public void init(){
        //短信具类配置初始化
        SmsUtil.initConfig(this);
    }

    public String getRegionId() {
        return regionId;
    }

    public SmsProerties setRegionId(String regionId) {
        this.regionId = regionId;
        return this;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public SmsProerties setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public SmsProerties setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
        return this;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public SmsProerties setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
        return this;
    }

    public String getSignName() {
        return signName;
    }

    public SmsProerties setSignName(String signName) {
        this.signName = signName;
        return this;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public String toString() {
        return "SmsProerties{" +
                "regionId='" + regionId + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessSecret='" + accessSecret + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", signName='" + signName + '\'' +
                ", messageType='" + messageType + '\'' +
                ", queueName='" + queueName + '\'' +
                '}';
    }
}
