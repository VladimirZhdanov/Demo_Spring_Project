package com.homel.demo.project.utils;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

import static com.homel.demo.project.error.ErrorMessages.USER_IS_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonUtilsTest {

    @Test
    public void shouldReturnMessageWithArgsStringWhenPassMap() {
        String errorMessage = CommonUtils.getExceptionMessage(USER_IS_NOT_FOUND.getValue(),
                List.of(Pair.of("id", "1"), Pair.of("name", "Tome")));

        assertEquals(USER_IS_NOT_FOUND.getValue() + ", arguments: id : 1, name : Tome", errorMessage);
    }

    @Test
    public void shouldReturnMessageStringWhenPassNull() {
        String errorMessage = CommonUtils.getExceptionMessage(USER_IS_NOT_FOUND.getValue(), null);

        assertEquals(USER_IS_NOT_FOUND.getValue(), errorMessage);
    }

    @Test
    public void shouldReturnMessageStringWhenPassEmptyMap() {
        String errorMessage = CommonUtils.getExceptionMessage(USER_IS_NOT_FOUND.getValue(), new ArrayList<>());

        assertEquals(USER_IS_NOT_FOUND.getValue(), errorMessage);
    }

    @Test
    public void shouldReturnEmptyMessageWhenPassNullMessage() {
        String errorMessage = CommonUtils.getExceptionMessage(null, List.of(Pair.of("id", "1"), Pair.of("name", "Tome")));

        assertEquals("", errorMessage);
    }
}