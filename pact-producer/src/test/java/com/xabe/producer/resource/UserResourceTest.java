package com.xabe.producer.resource;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


class UserResourceTest {

    private UserResource userResource;

    @BeforeEach
    public void setUp() throws Exception {
        this.userResource = new UserResource();
    }

    @Test
    public void shouldGetStatus() throws Exception {
        //Given

        //When
        final String result = this.userResource.getStatus();

        //Then
        assertThat(result, is(notNullValue()));

    }


    @Test
    public void givenAIdWhenInvokeGetUserThenReturnUser() throws Exception {
        //Given
        final int id = 1;

        //When
        final User result = userResource.getUser(id);

        //Then
        assertThat(result, is(notNullValue()));

    }

}