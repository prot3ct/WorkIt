using System.Collections.Generic;
using WorkIt_Server.BussinessLogic.Logics;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class BaseService
    {
        public BaseService()
        {
            this.WorkItDbContext = new WorkItDbContext();
            this.TaskLogic = new JobBussinessLogic(this.WorkItDbContext);
            this.LocationLogic = new LocationBussinessLogic(this.WorkItDbContext);
            this.UserLogic = new UserBussinessLogic(this.WorkItDbContext);
            this.TaskReportLogic = new JobReportBussinessLogic(this.WorkItDbContext);
            this.TaskRequestLogic = new JobRequestBussinessLogic(this.WorkItDbContext);
            this.UserReportLogic = new UserReportBussinessLogic(this.WorkItDbContext);
            this.RaitingLogic = new RaitingBussinessLogic(this.WorkItDbContext);
        }

        public WorkItDbContext WorkItDbContext { get; private set; }

        public JobBussinessLogic TaskLogic { get; private set; }
        
        public LocationBussinessLogic LocationLogic { get; private set; }

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
            LocationLogic.CreateLocation(taskInformation.ToLocation());
            var location = LocationLogic.GetLocationByInfo(taskInformation.ToLocation());
            TaskLogic.CreateTask(taskInformation, location.LocationId);
        }

        public IEnumerable<TaskDTO> GetAllTasks()
        {
            return TaskLogic.GetAllTasks();
        }

        public IEnumerable<TaskDTO> GetTasksByUser(int userId)
        {
            return TaskLogic.GetTasksByUser(userId);
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

        public void DeleteTaskRequestById(int taskId)
        {
            TaskRequestLogic.DeleteTaskRequest(taskId);
        }

        public IEnumerable<TaskRequestDTO> GetRequestsForCurrentUser(int userId)
        {
            return TaskRequestLogic.GetRequestsForCurrentUser(userId);
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

        public TaskRequestDTO GetTaskRequestById(int requestId)
        {
            return this.TaskRequestLogic.GetTaskRequestById(requestId);
        }

        public void CreateTaskRequestComment(CommentDTO comment)
        {
            this.TaskRequestLogic.CreateTaskRequestComment(comment);
        }

        public List<CommentDTO> GetCommentsByTaskRequestId(int taskRequestId)
        {
            return this.TaskRequestLogic.GetTaskRequestComments(taskRequestId);
        }
    }
}