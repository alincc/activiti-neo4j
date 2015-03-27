package org.activiti.neo4j;

import java.util.List;

import org.activiti.neo4j.*;
import org.activiti.neo4j.Command;
import org.activiti.neo4j.CommandContext;
import org.activiti.neo4j.CommandExecutor;
import org.activiti.neo4j.manager.TaskManager;
import org.activiti.neo4j.persistance.entities.Task;

public class TaskServiceImpl implements org.activiti.neo4j.TaskService {

  // TODO: can be put in a command service super class
  protected org.activiti.neo4j.CommandExecutor commandExecutor;
  protected TaskManager taskManager;

  public TaskServiceImpl(CommandExecutor commandExecutor) {
    this.commandExecutor = commandExecutor;
  }

  public List<Task> findTasksFor(final String assignee) {
    return commandExecutor.execute(new org.activiti.neo4j.Command<List<Task>>() {
      
      public void execute(org.activiti.neo4j.CommandContext<List<Task>> commandContext) {
        commandContext.setResult(taskManager.getTasksByAssignee(assignee));
      }
      
    });
  }

  public void complete(final long taskId) {
    commandExecutor.execute(new Command<Void>() {
      
      public void execute(CommandContext<Void> commandContext) {
        commandContext.signal(commandContext.getExecutionManager().getExecutionById(taskId));
      }
      
    });
  }

  public TaskManager getTaskManager() {
    return taskManager;
  }
  
  public void setTaskManager(TaskManager taskManager) {
    this.taskManager = taskManager;
  }

}
