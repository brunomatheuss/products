package com.bruno.products.integration;

import com.bruno.products.ProductsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ProductsApplication.class)
public class ComprasControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldRetornarComprasOrdenadasSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compras"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].cliente").exists());
    }

    @Test
    void shouldMaiorCompraPorAnoSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/maiorCompra/2020"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("cliente").exists());
    }

    @Test
    void shouldMaiorCompraPorAnoBadRequestAnoNaoEncontrado() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/maiorCompra/1999"))
                .andExpect(status().isNotFound());
    }

}
