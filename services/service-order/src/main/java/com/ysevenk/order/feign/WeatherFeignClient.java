package com.ysevenk.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "weather-client", url = "https://devapi.qweather.com")
public interface WeatherFeignClient {

    @GetMapping("/v7/weather/3d")
    String getWeather(@RequestParam("location") String location,
                      @RequestParam("key") String key);
}
