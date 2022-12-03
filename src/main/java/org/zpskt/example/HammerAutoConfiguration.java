package org.zpskt.example;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.zpskt.example.annotation.HammerScannerRegister;
import org.zpskt.example.properties.HammerConfigurationProperties;

@Configuration
@EnableConfigurationProperties({HammerConfigurationProperties.class})//设置当前类用的property
@Import({HammerScannerRegister.class}) //注入类到Bean中
public class HammerAutoConfiguration {
}
