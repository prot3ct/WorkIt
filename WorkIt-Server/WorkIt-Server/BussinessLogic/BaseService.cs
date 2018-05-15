﻿using System.Collections.Generic;
using WorkIt_Server.BussinessLogic.Logics;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;
using WorkIt_Server.Models.ViewModels;

namespace WorkIt_Server.BLL
{
    public class BaseService
    {
        public BaseService()
        {
            this.WorkItDbContext = new WorkItDbContext();
            this.TaskLogic = new TaskBussinessLogic(this.WorkItDbContext);
            this.UserLogic = new UserBussinessLogic(this.WorkItDbContext);
            this.TaskReportLogic = new JobReportBussinessLogic(this.WorkItDbContext);
            this.TaskRequestLogic = new TaskRequestBussinessLogic(this.WorkItDbContext);
            this.UserReportLogic = new UserReportBussinessLogic(this.WorkItDbContext);
            this.RaitingLogic = new RaitingBussinessLogic(this.WorkItDbContext);
        }

        public WorkItDbContext WorkItDbContext { get; private set; }

        public TaskBussinessLogic TaskLogic { get; private set; }

        public UserBussinessLogic UserLogic { get; private set; }

        public JobReportBussinessLogic TaskReportLogic { get; private set; }

        public TaskRequestBussinessLogic TaskRequestLogic { get; private set; }

        public UserReportBussinessLogic UserReportLogic { get; private set; }

        public RaitingBussinessLogic RaitingLogic { get; private set; }

        public LoginViewModel getUserInfo(string email)
        {
            return UserLogic.getUserInfo(email);
        }

        public bool LoginUser(LoginDTO credentials)
        {
            return UserLogic.LoginUser(credentials);
        }

        public bool RegisterUser(RegisterDTO credentials)
        {
            return UserLogic.RegisterUser(credentials);
        }

        public void CreateTask(CreateTaskDTO task)
        {
            TaskLogic.CreateTask(task);
        }

        public void UpdateTask(EditTaskDTO task)
        {
            TaskLogic.UpdateTask(task);
        }

        public IEnumerable<AvailableTasksViewModel> GetAllAvailableTasks()
        {
            return TaskLogic.GetAllAvailableTasks();
        }

        public IEnumerable<GetMyTasksListViewModel> GetMyTasks(int userId)
        {
            return TaskLogic.GetMyTasks(userId);
        }

        public IEnumerable<AssignedTasksListViewModel> GetAssignedTasks(int userId)
        {
            return TaskLogic.GetAssignedTasks(userId);
        }

        public IEnumerable<CompletedTasksListViewModel> GetCompletedTasksByUser(int userId)
        {
            return TaskLogic.GetCompletedTasksByUser(userId);
        }

        public TaskDetailsViewModel GetTaskDetails(int taskId)
        {
            return TaskLogic.GetTaskDetails(taskId);
        }

        public void DeleteTaskById(int taskId)
        {
            TaskLogic.DeleteTaskById(taskId);
        }

        public void CreateTaskRequest(CreateTaskRequestDTO jobRequest)
        {
            TaskRequestLogic.CreateTaskRequest(jobRequest);
        }

        public void UpdateTaskRequest(UpdateTaskRequestDTO taskRequest)
        {
            TaskRequestLogic.UpdateTaskRequest(taskRequest);
        }

        public IEnumerable<TaskRequestsListViewModel> GetRequestsForTask(int taskId)
        {
            return TaskRequestLogic.GetRequestsForTask(taskId);
        }

        public void DeleteTaskRequestById(int taskId)
        {
            TaskRequestLogic.DeleteTaskRequest(taskId);
        }

        public void CreateRaiting(CreateRaitingDTO raiting)
        {
            this.RaitingLogic.CreateRatiing(raiting);
        }

        //public void CreateTaskReport(TaskReportDTO jobReport)
        //{
        //    TaskReportLogic.CreateTaskReport(jobReport);
        //}

        //public void CreateUserReport(UserReportDTO userReport)
        //{
        //    UserReportLogic.CreateUserReport(userReport);
        //}

        //public IEnumerable<RaitingDTO> GetAllRaitingsByUserId(int userId)
        //{
        //    return this.RaitingLogic.GetAllRaitingByUserId(userId);
        //}
    }
}