package ru.rsreu.sovynhik.dairystore03;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.rsreu.sovynhik.dairystore01.controller.HomeController;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homePageShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void homePageShouldReturnHomeView() throws Exception {
        mockMvc.perform(get("/home")).andExpect(view().name("home"));
    }

    @Test
    void homePageShouldContainProductsList() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attribute("products", hasSize(4)))
                .andExpect(model().attribute("products", hasItem(containsString("Молоко 3,2%"))));
    }

    @Test
    void homePageShouldRenderProductsInHtml() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Домик в деревне")))
                .andExpect(content().string(containsString("Гауда")));
    }
}