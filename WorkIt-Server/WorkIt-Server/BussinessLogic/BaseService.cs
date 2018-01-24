using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.BussinessLogic.Logics;
using WorkIt_Server.Models;
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

        public IEnumerable<TaskDTO> GetAllJobs()
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

        public IEnumerable<CommentDTO> GetCommentsById(int id)
        {
            return CommentLogic.GetCommentsByTaskId(id);
        }

        public bool PostJobReport(TaskReportDTO jobReport)
        {
            return TaskReportLogic.CreateJobReport(jobReport);
        }
        public bool CreateJobRequest(TaskRequestDTO jobRequest)
        {
            return TaskRequestLogic.CreateJobRequest(jobRequest);
        }
        public bool CreateUserReport(UserReportDTO userReport)
        {
            return UserReportLogic.CreateUserReport(userReport);
        }
        public bool DeleteJobRequest(int id)
        {
            return TaskRequestLogic.DeleteJobRequest(id);
        }
        public bool DeleteCommentById(int jobId, int CommentId)
        {
            return CommentLogic.DeleteCommentById(jobId, CommentId);
        }
        public void AddRaiting(Raiting raiting)
        {
            this.RaitingLogic.CreateRatiing(raiting);
        }
    }
}