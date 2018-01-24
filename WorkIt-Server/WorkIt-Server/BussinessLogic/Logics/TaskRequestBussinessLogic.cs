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

        public bool CreateJobRequest(TaskRequestDTO jobRequest)
        {
            var jobRequestToBeInserted = new TaskRequest
            {
                Description = jobRequest.Description,
                TaskId = jobRequest.TaskId,
                UserId = jobRequest.UserId
            };

            db.TaskRequests.Add(jobRequestToBeInserted);
            db.SaveChanges();
            return true;
        }

        public bool DeleteJobRequest(int id)
        {
            var jobRequest = db.TaskRequests.Where(jr => jr.TaskRequestId == id).FirstOrDefault();

            db.TaskRequests.Remove(jobRequest);
            db.SaveChanges();
            return true;
        }
    }
}