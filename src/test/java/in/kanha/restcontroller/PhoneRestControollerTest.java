package in.kanha.restcontroller;// src/test/java/in/kanha/restcontroller/PhoneRestControollerTest.java


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.kanha.entity.Phone;
import in.kanha.service.PhoneServiceImpl;

@WebMvcTest(PhoneRestControoller.class)
class PhoneRestControollerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PhoneServiceImpl service;

    @Test
    @DisplayName("POST /crud/save returns CREATED and saved entity")
    void savePhone() throws Exception {
        Phone request = new Phone();
        request.setName("Pixel");
        request.setPrice(400);

        Phone saved = new Phone();
        saved.setId(1);
        saved.setName("Pixel");
        saved.setPrice(400);

        when(service.phoneSave(any())).thenReturn(saved);

        mvc.perform(post("/crud/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Pixel"));
    }

    @Test
    @DisplayName("GET /crud/get/{id} returns the phone")
    void getById() throws Exception {
        Phone p = new Phone();
        p.setId(2);
        p.setName("OnePlus");
        p.setPrice(350);

        when(service.getById(2)).thenReturn(p);

        mvc.perform(get("/crud/get/2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(2))
            .andExpect(jsonPath("$.name").value("OnePlus"));
    }

    @Test
    @DisplayName("GET /crud/getall returns list")
    void getAll() throws Exception {
        Phone p1 = new Phone(); p1.setId(1); p1.setName("A");
        Phone p2 = new Phone(); p2.setId(2); p2.setName("B");
        when(service.getAll()).thenReturn(List.of(p1, p2));

        mvc.perform(get("/crud/getall"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    @DisplayName("DELETE /crud/delete/{id} returns NO_CONTENT")
    void deleteById() throws Exception {
        mvc.perform(delete("/crud/delete/3"))
            .andExpect(status().isNoContent());

        verify(service).deleteById(3);
    }

    @Test
    @DisplayName("PUT /crud/put/{id} updates and returns OK")
    void update() throws Exception {
        Phone existing = new Phone(); existing.setId(5); existing.setName("Old"); existing.setPrice(200);
        Phone updateReq = new Phone(); updateReq.setName("New"); updateReq.setPrice(250);
        Phone updated = new Phone(); updated.setId(5); updated.setName("New"); updated.setPrice(250);

        when(service.getById(5)).thenReturn(existing);
        when(service.phoneSave(any())).thenReturn(updated);

        mvc.perform(put("/crud/put/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateReq)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(5))
            .andExpect(jsonPath("$.name").value("New"));
    }
}
