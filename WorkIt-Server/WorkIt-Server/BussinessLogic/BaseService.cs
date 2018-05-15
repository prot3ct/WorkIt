using System.Collections.Generic;
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
            this.TaskLogic = new JobBussinessLogic(this.WorkItDbContext);
            this.UserLogic = new UserBussinessLogic(this.WorkItDbContext);
            this.TaskReportLogic = new JobReportBussinessLogic(this.WorkItDbContext);
            this.TaskRequestLogic = new JobRequestBussinessLogic(this.WorkItDbContext);
            this.UserReportLogic = new UserReportBussinessLogic(this.WorkItDbContext);
            this.RaitingLogic = new RaitingBussinessLogic(this.WorkItDbContext);
        }

        public WorkItDbContext WorkItDbContext { get; private set; }

        public JobBussinessLogic TaskLogic { get; private set; }
        

        public UserBussinessLogic UserLogic { get; private set; }

        public JobReportBussinessLogic TaskReportLogic { get; private set; }

        public JobRequestBussinessLogic TaskRequestLogic { get; private set; }

        public UserReportBussinessLogic UserReportLogic { get; private set; }

        public RaitingBussinessLogic RaitingLogic { get; private set; }

        public int GetUserIdByEmail(string email)
        {
            return UserLogic.GetUserIdByEmail(email);
        }

        public bool LoginUser(LoginDTO credentials)
        {
            return UserLogic.LoginUser(credentials);
        }

        public bool RegisterUser(RegisterDTO credentials)
        {
            return UserLogic.RegisterUser(credentials);
        }

        public void CreateTask(TaskDTO taskInformation)
        {
            TaskLogic.CreateTask(taskInformation);
        }

        public void UpdateTask(TaskDTO task)
        {
            TaskLogic.UpdateTask(task);
        }

        public IEnumerable<TaskDTO> GetAllAvailableTasks()
        {
            return TaskLogic.GetAllAvailableTasks();
        }

        public IEnumerable<TaskDTO> GetTasksByUser(int userId)
        {
            return TaskLogic.GetTasksByUser(userId);
        }

        public IEnumerable<TaskDTO> GetCompletedTasksByUser(int userId)
        {
            return TaskLogic.GetCompletedTasksByUser(userId);
        }

        public void DeleteTaskById(int taskId)
        {
            TaskLogic.DeleteTaskById(taskId);
        }

        public TaskDTO GetTaskById(int taskId)
        {
            return TaskLogic.GetTaskById(taskId);
        }

        public void CreateTaskRequest(TaskRequestDTO jobRequest)
        {
            TaskRequestLogic.CreateTaskRequest(jobRequest);
        }

        public void UpdateTaskRequest(TaskRequestDTO taskRequest)
        {
            TaskRequestLogic.UpdateTaskRequest(taskRequest);
        }

        public void DeleteTaskRequestById(int taskId)
        {
            TaskRequestLogic.DeleteTaskRequest(taskId);
        }

        public  IEnumerable<TaskRequestListViewModel> GetRequestsForTask(int taskId)
        {
            return TaskRequestLogic.GetRequestsForTask(taskId);
        }

        public void CreateTaskReport(TaskReportDTO jobReport)
        {
            TaskReportLogic.CreateTaskReport(jobReport);
        }

        public void CreateUserReport(UserReportDTO userReport)
        {
            UserReportLogic.CreateUserReport(userReport);
        }

        public void CreateRaiting(RaitingDTO raiting)
        {
            this.RaitingLogic.CreateRatiing(raiting);
        }

        public IEnumerable<RaitingDTO> GetAllRaitingsByUserId(int userId)
        {
            return this.RaitingLogic.GetAllRaitingByUserId(userId);
        }
    }
}