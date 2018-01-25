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
            this.CommentLogic = new CommentBussinessLogic(this.WorkItDbContext);
            this.TaskReportLogic = new JobReportBussinessLogic(this.WorkItDbContext);
            this.TaskRequestLogic = new JobRequestBussinessLogic(this.WorkItDbContext);
            this.UserReportLogic = new UserReportBussinessLogic(this.WorkItDbContext);
            this.RaitingLogic = new RaitingBussinessLogic(this.WorkItDbContext);
        }

        public WorkItDbContext WorkItDbContext { get; private set; }

        public JobBussinessLogic TaskLogic { get; private set; }
        
        public LocationBussinessLogic LocationLogic { get; private set; }

        public UserBussinessLogic UserLogic { get; private set; }
        
        public CommentBussinessLogic CommentLogic { get; private set; }

        public JobReportBussinessLogic TaskReportLogic { get; private set; }

        public JobRequestBussinessLogic TaskRequestLogic { get; private set; }

        public UserReportBussinessLogic UserReportLogic { get; private set; }

        public RaitingBussinessLogic RaitingLogic { get; private set; }

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
            var location = LocationLogic.CreateLocation(taskInformation.ToLocation());
            TaskLogic.CreateTask(taskInformation, location.LocationId);
        }

        public IEnumerable<TaskDTO> GetAllTasks()
        {
            return TaskLogic.GetAllTasks();
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

        public IEnumerable<TaskRequestDTO> GetRequestsForCurrentTask(int taskId)
        {
            return TaskRequestLogic.GetRequestsForCurrentTask(taskId);
        }

        public void DeleteCommentById(int commentId)
        {
            CommentLogic.DeleteCommentById(commentId);
        }

        public void DeleteCommentsByTaskId(int taskId)
        {
            CommentLogic.DeleteCommentsByTaskId(taskId);
        }

        public IEnumerable<CommentDTO> GetCommentsByTaskId(int taskId)
        {
            return CommentLogic.GetCommentsByTaskId(taskId);
        }

        public void CreateComment(CommentDTO comment)
        {
            CommentLogic.CreateComment(comment);
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