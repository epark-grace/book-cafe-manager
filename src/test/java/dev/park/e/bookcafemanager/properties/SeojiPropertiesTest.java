package dev.park.e.bookcafemanager.properties;

import dev.park.e.bookcafemanager.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource("classpath:application.properties")
class SeojiPropertiesTest {

    @Autowired
    SeojiProperties seojiProperties;

    @Test
    void 서지_키_조회() {
        assertThat(seojiProperties.getCertKey()).isNotNull();
        assertThat(seojiProperties.getResultStyle()).isNotNull();
        assertThat(seojiProperties.getPageNo()).isNotNull();
        assertThat(seojiProperties.getPageSize()).isNotNull();
    }

}