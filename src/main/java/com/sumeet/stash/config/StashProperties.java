package com.sumeet.stash.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "stash")
@Component
public class StashProperties {
    private String appName;
    private int maxBookmarksPerUser;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getMaxBookmarksPerUser() {
        return maxBookmarksPerUser;
    }

    public void setMaxBookmarksPerUser(int maxBookmarksPerUser) {
        this.maxBookmarksPerUser = maxBookmarksPerUser;
    }
}
