package com.ll.ticket.global.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    @DisplayName("RedisTemplate 테스트")
    void test() {
        // Given
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "key";
        String value = "test";

        // When
        valueOperations.set(key, value);

        // Then
        String getValue = valueOperations.get(key);
        assertThat(getValue).isEqualTo(value);
    }

}
