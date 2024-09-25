package com.phaete.backend.controller;

import com.phaete.backend.model.Status;
import com.phaete.backend.model.Todo;
import com.phaete.backend.repository.KanbanToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KanbanToDoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private KanbanToDoRepository kanbanToDoRepository;

    @DirtiesContext
    @Test
    void findAll_shouldReturnAllTodos_whenEmpty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @DirtiesContext
    @Test
    void findAll_shouldReturnAllTodos_withTodos() throws Exception {
        kanbanToDoRepository.save(new Todo("1", "test", Status.OPEN));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                [
                  {
                    "id": "1",
                    "description": "test",
                    "status": "OPEN"
                  }
                ]
                """));
    }

    @DirtiesContext
    @Test
    void save_shouldReturnTheNewTodo() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/todo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "description": "test",
                            "status": "OPEN"
                        }
                    """)
                )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "description": "test",
                        "status": "OPEN"
                    }
                """));
    }
    @DirtiesContext
    @Test
    void findById_shouldThrow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                    {
                        "exceptionClass" :"java.util.NoSuchElementException",
                        "message" : "Can not find todo with the id: 1"
                    }
                """));
    }

    @DirtiesContext
    @Test
    void findById_shouldReturnTheTodo() throws Exception {
        kanbanToDoRepository.save(new Todo("1", "test", Status.OPEN));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id": "1",
                        "description": "test",
                        "status": "OPEN"
                    }
                """));
    }

    @DirtiesContext
    @Test
    void update_shouldReturnTheUpdatedTodo() throws Exception {
        kanbanToDoRepository.save(new Todo("1", "test", Status.OPEN));

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/todo/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                            """
                                {
                                    "description": "test",
                                    "status": "IN_PROGRESS"
                                }
                            """)
                )
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                      "id": "1",
                      "description": "test",
                      "status": "IN_PROGRESS"
                    }
                """));
    }

    @DirtiesContext
    @Test
    void update_shouldThrow() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                        {
                            "description": "test",
                            "status": "IN_PROGRESS"
                        }
                    """
                        )
                )
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                    {
                      "exceptionClass" :"java.util.NoSuchElementException",
                      "message" : "Can not find todo with the id: 1"
                    }
                """));
    }

    @DirtiesContext
    @Test
    void delete_shouldReturnSuccessfulMessage() throws Exception {
        kanbanToDoRepository.save(new Todo("1", "test", Status.OPEN));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted todo with id: 1"));

    }

    @DirtiesContext
    @Test
    void delete_shouldThrow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                    {
                      "exceptionClass" :"java.lang.AssertionError",
                      "message" : "Could not complete request."
                    }
                """));

    }

}