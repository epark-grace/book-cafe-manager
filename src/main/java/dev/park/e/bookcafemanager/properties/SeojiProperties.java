package dev.park.e.bookcafemanager.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "seoji")
public class SeojiProperties {
    private final String certKey;
    private final String resultStyle;
    private final String pageNo;
    private final String pageSize;
}
