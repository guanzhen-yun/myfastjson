package com.inke.fastjson;

import com.inke.fastjson.model.OrderInfo;
import com.inke.fastjson.model.UserInfo;
import com.inke.library.JSON;

import org.junit.Test;

public class FastJsonTest {

    private String json;

    @Test
    public void parse() {
        UserInfo user = new UserInfo();
        user.setuId("0");
        user.setNickName("simon");
        user.setRealName("彭锡");
        user.setEmail("simon@cmonbaby.com");
        user.setAddress("网易杭州研究院");
        user.setPhoneNumber("187*****257");
        user.setUserType(3);
        user.setHeaderImage("https://www.cmonbaby.com/images/avatar.jpg");

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setoId("$16320190729010001");
        orderInfo.setCategoryId("C16306006");
        orderInfo.setGoodName("网易严选-网易自家的味央黑猪肉");
//        orderInfo.setPrice(new BigDecimal("52.163"));
        orderInfo.setCreateDate(System.currentTimeMillis());
        orderInfo.setUserInfo(user);

        long t1 = System.currentTimeMillis();
        System.out.println("FastJson序列化: ----------------------");
        json = JSON.toJSONString(orderInfo);
        System.out.println(json);
        System.out.println("耗时: " + (System.currentTimeMillis() - t1) + " ms");
    }
}
