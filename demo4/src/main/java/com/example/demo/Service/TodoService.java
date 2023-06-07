package com.example.demo.Service;

import com.example.demo.ApiExeption.ApiExeption;
import com.example.demo.Model.MyUser;
import com.example.demo.Model.Todo;
import com.example.demo.Repository.AuthRepository;
import com.example.demo.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TodoService {
  private final TodoRepository todoRepository;
  private final AuthRepository authRepository;

  public List<Todo> getTodos(Integer id) {
//
      return todoRepository.findTodoByUserId(id);
    }
    public void addTodo(Integer userid, Todo todo) {
      MyUser myUser= authRepository.findMyUserById(userid);
      todo.setUser(myUser);
      todoRepository.save(todo);
    }
  public void updateTodo(Integer userid, Todo todo,Integer todoId) {
    Todo oldTodo = todoRepository.findTodoById(todoId);
    if (oldTodo == null) {

      throw new ApiExeption("todo Not found");
    }
    if (oldTodo.getUser().getId() != userid) {
      throw new ApiExeption("Erorre,Unauthorize this Todo");
    }
    oldTodo.setMassage(todo.getMassage());
    todoRepository.save(oldTodo);
  }
  public void deleteTodo(Integer userid,Integer todoId) {
    Todo oldTodo = todoRepository.findTodoById(todoId);
    if (oldTodo== null) {

      throw new ApiExeption("Todo Not found");
    }
    if(oldTodo.getUser().getId()!=userid) {
      throw new ApiExeption("UAuthorized, this Todo does NOT belong to you!");
    }
    todoRepository.delete(oldTodo);

  }
}
