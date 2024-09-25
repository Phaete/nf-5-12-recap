package com.phaete.backend.controller;

import com.phaete.backend.model.ErrorMessage;
import com.phaete.backend.model.TodoDTO;
import com.phaete.backend.model.Todo;
import com.phaete.backend.service.KanbanToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/todo")
public class KanbanToDoController {

    private final KanbanToDoService kanbanToDoService;

    public KanbanToDoController(KanbanToDoService kanbanToDoService) {
        this.kanbanToDoService = kanbanToDoService;
    }

    @GetMapping
    public List<Todo> findAll() {
        return kanbanToDoService.findAll();
    }

    @PostMapping
    public TodoDTO save(@RequestBody TodoDTO todoDTO) {
        Todo newTodo = kanbanToDoService.save(todoDTO);
        return new TodoDTO(newTodo.description(), newTodo.status());
    }

    @GetMapping("/{id}")
    public Todo findById(@PathVariable String id) {
        return kanbanToDoService.findById(id);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable String id, @RequestBody Todo todo) {
        return kanbanToDoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return kanbanToDoService.deleteById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoSuchElementException(NoSuchElementException e) {
        return new ErrorMessage(e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(AssertionError.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleAssertionError(AssertionError e) {
        return new ErrorMessage(e.getClass().getName(), "Could not complete request.");
    }


}
