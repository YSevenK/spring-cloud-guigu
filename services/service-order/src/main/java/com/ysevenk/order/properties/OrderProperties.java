package com.ysevenk.order.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "order")  // 配置批量绑定
public class OrderProperties {
    String timeout;

    String autoConfirm;

    String dbUrl;
}
