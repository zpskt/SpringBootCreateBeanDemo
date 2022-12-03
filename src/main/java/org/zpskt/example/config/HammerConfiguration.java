package org.zpskt.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HammerConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    private static Logger log = LoggerFactory.getLogger(HammerConfiguration.class);

    private final static Map<String, HammerConfiguration> CONFIGURATION_CACHE = new ConcurrentHashMap<>();

    /**
     * 默认配置，再用户没有定义任何全局配置时使用该默认配置
     */
    private final static HammerConfiguration defaultConfiguration = configuration();

    /**
     * 实例化HammerConfiguration对象，并初始化默认值
     *
     * @return 新创建的HammerConfiguration实例
     */
    public static HammerConfiguration configuration() {
        return configuration("hammerConfiguration");
    }

    /**
     * 实例化HammerConfiguration对象，并初始化默认值
     *
     * @param id 配置ID
     * @return 新创建的HammerConfiguration实例
     */
    public static HammerConfiguration configuration(String id) {
        HammerConfiguration configuration = HammerConfiguration.CONFIGURATION_CACHE.get(id);
        if (configuration == null) {
            synchronized (HammerConfiguration.class) {
                if (!CONFIGURATION_CACHE.containsKey(id)) {
                    configuration = createConfiguration();
                    configuration.setId(id);
                    CONFIGURATION_CACHE.put(id, configuration);
                }
            }
        }
        return CONFIGURATION_CACHE.get(id);
    }
    public static HammerConfiguration createConfiguration() {
        HammerConfiguration configuration = new HammerConfiguration();
        configuration.setId("hammerConfiguration" + configuration.hashCode());
        return configuration;
    }
}
