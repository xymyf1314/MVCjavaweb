package com.neuedu.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.captcha.v20190722.CaptchaClient;

import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultRequest;
import com.tencentcloudapi.captcha.v20190722.models.DescribeCaptchaResultResponse;

public class SlidingUtil {
    public static String slid(String ticket, String randstr) {
        try {

            Credential cred = new Credential("AKIDVO6cmmw1II93uXhHUSAdwHOFqCI92icJ", "g6E9pFQPAfutc6KK5apreBSTH8l9MK53");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("captcha.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            CaptchaClient client = new CaptchaClient(cred, "ap-beijing", clientProfile);

            String params = "{\"CaptchaType\":9,\"Ticket\":\""+ticket+"\",\"UserIp\":\"127.0.0.1\",\"Randstr\":\""+randstr+"\",\"CaptchaAppId\":2096417182,\"AppSecretKey\":\"01bwasyixjqTzzBBcGCVh_w**\"}";
            DescribeCaptchaResultRequest req = DescribeCaptchaResultRequest.fromJsonString(params, DescribeCaptchaResultRequest.class);

            DescribeCaptchaResultResponse resp = client.DescribeCaptchaResult(req);
            return DescribeCaptchaResultRequest.toJsonString(resp);
//            System.out.println(DescribeCaptchaResultRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

        return ticket;
    }

    public static void main(String[] args) {
        String slid = slid("t02MOoZbXQq6hH0v3oUNBCuJ1d9-ibOM79u-l-iHq2gNSMD7eW…-pgOky6BSqG6bDE8iBsqmvYrB8PpMhuSHtXeRjDszcmIGTQ**", "@j81");
        System.out.println(slid);
    }

}