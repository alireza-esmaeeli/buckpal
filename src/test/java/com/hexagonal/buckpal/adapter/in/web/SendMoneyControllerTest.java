package com.hexagonal.buckpal.adapter.in.web;

import com.hexagonal.buckpal.application.domain.model.Account;
import com.hexagonal.buckpal.application.domain.model.Money;
import com.hexagonal.buckpal.application.port.in.SendMoneyCommand;
import com.hexagonal.buckpal.application.port.in.SendMoneyUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.hexagonal.buckpal.application.domain.model.Account.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {SendMoneyController.class})
class SendMoneyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SendMoneyUseCase sendMoneyUseCase;

    @Test
    void testSendMoney() throws Exception {

        mockMvc.perform(post("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}",
                        41L, 42L, 500)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());

        then(sendMoneyUseCase).should()
                .sendMoney(eq(new SendMoneyCommand(
                        new AccountId(41L),
                        new AccountId(42L),
                        Money.of(500L)
                )));
    }
}