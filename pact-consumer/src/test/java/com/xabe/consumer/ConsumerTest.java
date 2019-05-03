package com.xabe.consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "user-provider", port = "8888", hostInterface = "localhost", providerType = ProviderType.SYNCH)
public class ConsumerTest {

    private ConsumerUser consumerUser;

    @BeforeEach
    public void setUp() throws Exception {
        this.consumerUser = new ConsumerUser();
    }


    @Pact(state = "User 1 exists", provider = "user-provider", consumer = "user-consumer")
    RequestResponsePact getPersonPact(PactDslWithProvider builder) {
        // @formatter:off
        return builder
                .given("User 1 exists")
                .uponReceiving("A request to /user-service/user/1") //description of the contract
                .path("/user-service/user/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .matchHeader("Content-Type", "application/json")
                .headers(Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody()
                        .stringType("firstName", "Zaphod")
                        .stringType("lastName", "Beeblebrox"))
                .toPact();
        // @formatter:on
    }


    @PactTestFor(pactMethod = "getPersonPact")
    @Test
    public void givenAIdPersonWhenInvokeGetUserThenReturnUser() throws Exception {
        //Given
        final int id = 1;

        //When
        final User result  = consumerUser.getUser(id);

        //Then
        assertThat(result, is(notNullValue()));
        assertThat(result.getFirstName(), is(notNullValue()));
        assertThat(result.getLastName(), is(notNullValue()));

    }
}
