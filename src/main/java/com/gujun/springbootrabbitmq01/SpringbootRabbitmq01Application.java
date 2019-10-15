package com.gujun.springbootrabbitmq01;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@SpringBootApplication
public class SpringbootRabbitmq01Application extends WebMvcConfigurationSupport {

    /*
        rabbitmq简单的消息推送、接受流程：
            消息推送服务，将消息推送到rabbitMq服务器，然后经过服务器里面的交换机、队列等各种关系（后面会详细讲）将数据处理入列后，
            最终消费者获取对应监听的消息；
        常用的交换机如下，因为消费者从队列中获取信息的，而队列是绑定交换机的(通常)，所以对应的消息推送/接收模式也有如下几种：
            1.Direct Exchange：直连型交换机，根据消息携带的路由键将消息传递给对应队列；有一个队列绑定到一个直连交换机上，同时赋予一个路由键，
        然后当一个消费携带着路由值为x，该消息通过生产者发送给交换机时，交换机就会根据该路由键去寻找该键对应的队列；
            2.Fanout Exchange: 扇形交换机，该交换机没有路由键概念，就算绑定了键也会被无视，该交换机接收到消息后，会转发到绑定在它上的所有队列；
            3.Topic Exchange: 主题交换机，该交换机其实跟直连交换机差不多，但其特点就是在路由键和绑定键之间是有规则的；
        *表示一个必须出现的字符，#表示任意数量的字符；
        主题交换机非常强大，因为当当一个队列的绑定键为#时，该队列就会无视消息的路由键，接收所有信息(扇形交换机)；
        当*和#都未出现在队列绑定键中时，该主题交换机就拥有直连交换机的行为；

     */

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmq01Application.class, args);
    }

    //配置fastjson
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters中
        converters.add(fastConverter);
        //5、追加默认转换器
        super.addDefaultHttpMessageConverters(converters);
    }

}
