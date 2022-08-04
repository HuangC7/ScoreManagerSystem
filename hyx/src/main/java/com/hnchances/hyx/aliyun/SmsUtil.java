package com.hnchances.hyx.aliyun;

/**
 * author: yuexuan_huang@126.com
 * Date: 2022/8/2
 * Description:短信工具类
 */
import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: SmsUtill
 * @Description: 短信工具类
 * @Author: james
 * @Version 1.0
 **/
public class SmsUtil {
    private final static Logger logger = LoggerFactory.getLogger(SmsUtil.class);
    /**
     * 发送异常结果
     */
    private final static String EXCEPTION = "发送短信异常！";
    /**
     * 验证码发送成功结果
     */
    public final static String OK = "OK";

    private static SmsProerties smsProerties;

    /**
     * @Title: initConfig
     * @Description: 配置初始化
     * @Author: james
     * @param: smsProertiesInit
     * @throws:
     */
    public static void initConfig(SmsProerties smsProertiesInit){
        smsProerties = smsProertiesInit;
    }

    /**
     * @Title: sendSms
     * @Description: 发送短信验证码
     * @Author: james
     * @param: phoneNumbers 手机号，多个手机号用英文逗号隔开，最多支持1000个
     * @param: phoneNumbers,name,meeting,address,time 短信内容
     * @return: java.lang.String 返回调用结果 成功=>"OK"；失败=>错误信息
     * @throws:
     */
    public  static  String sendSms(String phoneNumbers,String code)  {
        DefaultProfile profile = DefaultProfile.getProfile(smsProerties.getRegionId(), smsProerties.getAccessKeyId(), smsProerties.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println(smsProerties);
        System.out.println("+++++++++++++++++++++++++++++++++++");
        request.putQueryParameter("TemplateCode", smsProerties.getTemplateCode());
        request.putQueryParameter("SignName", smsProerties.getSignName());
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            return JSON.parseObject(response.getData()).getString("BizId");
        } catch (ServerException e) {
            logger.error(EXCEPTION,e);
        } catch (ClientException e) {
            logger.error(EXCEPTION,e);
        }catch (Exception e){
            logger.error(EXCEPTION,e);
        }
        return EXCEPTION;
    }
}

