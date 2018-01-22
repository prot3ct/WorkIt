using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class JobRequestBussinessLogic
    {
        private WorkItDbContext db;

        public JobRequestBussinessLogic(WorkItDbContext db)
        {
            this.Db = db;
        }

        public WorkItDbContext Db
        {
            get
            {
                return this.db;
            }
            set
            {
                this.db = value;
            }
        }

        public bool CreateJobRequest(JobRequestDTO jobRequest)
        {
            var jobRequestToBeInserted = new JobRequest
            {
                Description = jobRequest.Description,
                JobId = jobRequest.JobId,
                UserId = jobRequest.UserId
            };

            db.JobRequests.Add(jobRequestToBeInserted);
            db.SaveChanges();
            return true;
        }

        public bool DeleteJobRequest(int id)
        {
            var jobRequest = db.JobRequests.Where(jr => jr.JobRequestId == id).FirstOrDefault();

            db.JobRequests.Remove(jobRequest);
            db.SaveChanges();
            return true;
        }
    }
}