using System.Collections.Generic;
using System.Linq;
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

        public IEnumerable<TaskRequestDTO> GetRequestsForCurrentTask(int taskId)
        {
            return Db.TaskRequests.Select(tr => new TaskRequestDTO
            {
                Description = tr.Description,
                TaskId = tr.TaskId,
                UserId = tr.UserId
            }).ToList();
        }

        public void CreateTaskRequest(TaskRequestDTO jobRequest)
        {
            var jobRequestToBeInserted = new TaskRequest
            {
                Description = jobRequest.Description,
                TaskId = jobRequest.TaskId,
                UserId = jobRequest.UserId
            };

            Db.TaskRequests.Add(jobRequestToBeInserted);
            Db.SaveChanges();
        }

        public void DeleteTaskRequest(int taskId)
        {
            var jobRequest = Db.TaskRequests.Where(jr => jr.TaskRequestId == taskId).FirstOrDefault();

            Db.TaskRequests.Remove(jobRequest);
            Db.SaveChanges();
        }
    }
}