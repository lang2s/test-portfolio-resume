package com.resume.portfolio.test.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬북_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        // then
        System.out.println(helloResponseDto.getName());
        assertThat(helloResponseDto.getName()).isEqualTo(name);
        System.out.println(helloResponseDto.getAmount());
        assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }

}
