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
            this.JobLogic = new JobBussinessLogic(this.WorkItDbContext);
            this.LocationLogic = new LocationBussinessLogic(this.WorkItDbContext);
            this.UserLogic = new UserBussinessLogic(this.WorkItDbContext);
            this.CommentLogic = new CommentBussinessLogic(this.WorkItDbContext);
            this.JobReportLogic = new JobReportBussinessLogic(this.WorkItDbContext);
            this.JobRequestLogic = new JobRequestBussinessLogic(this.WorkItDbContext);
            this.UserReportLogic = new UserReportBussinessLogic(this.WorkItDbContext);
            this.RaitingLogic = new RaitingBussinessLogic(this.WorkItDbContext);
        }

        public WorkItDbContext WorkItDbContext { get; private set; }

        public JobBussinessLogic JobLogic { get; private set; }
        
        public LocationBussinessLogic LocationLogic { get; private set; }

        public UserBussinessLogic UserLogic { get; private set; }
        
        public CommentBussinessLogic CommentLogic { get; private set; }

        public JobReportBussinessLogic JobReportLogic { get; private set; }

        public JobRequestBussinessLogic JobRequestLogic { get; private set; }

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

        public bool CreateJob(TaskDTO jobInformation)
        {
            var location = LocationLogic.CreateLocation(jobInformation.ToLocation());
            return JobLogic.CreateJob(jobInformation, location.LocationId);
        }

        public IEnumerable<TaskDTO> GetAllJobs()
        {
            return JobLogic.GetAllJobs();
        }

        public IEnumerable<CommentDTO> GetCommentsById(int id)
        {
            return CommentLogic.GetCommentsById(id);
        }

        public bool PostJobReport(TaskReportDTO jobReport)
        {
            return JobReportLogic.CreateJobReport(jobReport);
        }
        public bool CreateJobRequest(TaskRequestDTO jobRequest)
        {
            return JobRequestLogic.CreateJobRequest(jobRequest);
        }
        public bool CreateUserReport(UserReportDTO userReport)
        {
            return UserReportLogic.CreateUserReport(userReport);
        }
        public bool DeleteJobRequest(int id)
        {
            return JobRequestLogic.DeleteJobRequest(id);
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