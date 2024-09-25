package com.phaete.backend.service;

import com.phaete.backend.model.Status;
import com.phaete.backend.model.Todo;
import com.phaete.backend.model.TodoDTO;
import com.phaete.backend.repository.KanbanToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class KanbanToDoServiceTest {

    private final KanbanToDoRepository kanbanToDoRepository = mock(KanbanToDoRepository.class);
    private final IdService idService = mock(IdService.class);


    @Test
    void findAll() {
        List<Todo> expectedTodoList = List.of(new Todo("1", "test", Status.OPEN));
        when(kanbanToDoRepository.findAll()).thenReturn(expectedTodoList);

        KanbanToDoService kanbanToDoService = new KanbanToDoService(kanbanToDoRepository, idService);

        List<Todo> result = kanbanToDoService.findAll();
        verify(kanbanToDoRepository).findAll();
        assertEquals(expectedTodoList, result);
    }

    @Test
    void findById() {
        Todo expectedTodo = new Todo("1", "test", Status.OPEN);
        when(kanbanToDoRepository.findById("1")).thenReturn(Optional.of(expectedTodo));

        KanbanToDoService kanbanToDoService = new KanbanToDoService(kanbanToDoRepository, idService);

        Todo result = kanbanToDoService.findById("1");
        verify(kanbanToDoRepository).findById("1");
        assertEquals(expectedTodo, result);
    }

    @Test
    void save() {
        Todo expectedTodo = new Todo("1", "test", Status.OPEN);
        when(idService.generateId()).thenReturn("1");
        when(kanbanToDoRepository.save(any(Todo.class))).thenReturn(expectedTodo);

        KanbanToDoService kanbanToDoService = new KanbanToDoService(kanbanToDoRepository, idService);

        Todo result = kanbanToDoService.save(new TodoDTO("test", Status.OPEN));
        verify(kanbanToDoRepository).save(any(Todo.class));
        assertEquals(expectedTodo, result);
    }

    @Test
    void update() {
        Todo expectedTodo = new Todo("1", "test", Status.IN_PROGRESS);
        when(kanbanToDoRepository.findById("1")).thenReturn(Optional.of(new Todo("1", "test", Status.OPEN)));
        when(kanbanToDoRepository.save(any(Todo.class))).thenReturn(expectedTodo);

        KanbanToDoService kanbanToDoService = new KanbanToDoService(kanbanToDoRepository, idService);

        Todo result = kanbanToDoService.update("1", expectedTodo);
        verify(kanbanToDoRepository).save(any(Todo.class));
        assertEquals(expectedTodo, result);
    }

    @Test
    void deleteById() {
        String expected = "Deleted todo with id: 1";

        KanbanToDoService kanbanToDoService = new KanbanToDoService(kanbanToDoRepository, idService);

        String result = kanbanToDoService.deleteById("1");
        verify(kanbanToDoRepository).deleteById("1");
        assertEquals(expected, result);
    }
}