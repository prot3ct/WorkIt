using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
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
            this.JobLogic = new JobBussinessLogic();
            this.LocationLogic = new LocationBussinessLogic();
            this.UserLogic = new UserBussinessLogic();
            this.CommentLogic = new CommentBussinessLogic();
            this.JobReportLogic = new JobReportBussinessLogic();
            this.JobRequestLogic = new JobRequestBussinessLogic();
            this.UserReportLogic = new UserReportBussinessLogic();
        }

        public WorkItDbContext WorkItDbContext { get; private set; }

        public JobBussinessLogic JobLogic { get; private set; }
        
        public LocationBussinessLogic LocationLogic { get; private set; }

        public UserBussinessLogic UserLogic { get; private set; }
        
        public CommentBussinessLogic CommentLogic { get; private set; }

        public JobReportBussinessLogic JobReportLogic { get; private set; }

        public JobRequestBussinessLogic JobRequestLogic { get; private set; }

        public UserReportBussinessLogic UserReportLogic { get; private set; }

        public bool LoginUser(LoginDTO credentials)
        {
            return UserLogic.LoginUser(WorkItDbContext, credentials);
        }

        public bool RegisterUser(RegisterDTO credentials)
        {
            return UserLogic.RegisterUser(WorkItDbContext, credentials);
        }

        public bool CreateJob(JobDTO jobInformation)
        {
            var location = LocationLogic.CreateLocation(WorkItDbContext, jobInformation.ToLocation());
            return JobLogic.CreateJob(WorkItDbContext, jobInformation, location.LocationId);
        }

        public IEnumerable<JobDTO> GetAllJobs()
        {
            return JobLogic.GetAllJobs(WorkItDbContext);
        }

        public IEnumerable<CommentDTO> GetCommentsById(int id)
        {
            return CommentLogic.GetCommentsById(WorkItDbContext, id);
        }

        public bool PostJobReport(JobReportDTO jobReport)
        {
            return JobReportLogic.CreateJobReport(WorkItDbContext, jobReport);
        }
        public bool CreateJobRequest(JobRequestDTO jobRequest)
        {
            return JobRequestLogic.CreateJobRequest(WorkItDbContext, jobRequest);
        }
        public bool CreateUserReport(UserReportDTO userReport)
        {
            return UserReportLogic.CreateUserReport(WorkItDbContext, userReport);
        }
        public bool DeleteJobRequest(int id)
        {
            return JobRequestLogic.DeleteJobRequest(WorkItDbContext, id);
        }
        public bool DeleteCommentById(int jobId, int CommentId)
        {
            return CommentLogic.DeleteCommentById(WorkItDbContext, jobId, CommentId);
        }
    }
}