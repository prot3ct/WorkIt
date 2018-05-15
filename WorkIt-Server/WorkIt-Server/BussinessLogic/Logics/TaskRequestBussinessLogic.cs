using System;
using System.Collections.Generic;
using System.Linq;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;
using WorkIt_Server.Models.ViewModels;

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

        public void UpdateTaskRequest(TaskRequestDTO taskRequest)
        {
            var updatedTaskRequest = db.TaskRequests.FirstOrDefault(tr => tr.TaskRequestId == taskRequest.TaskRequestId);
            updatedTaskRequest.RequestStatusId = taskRequest.RequestStatusId;

            if (updatedTaskRequest.RequestStatusId == 27)
            {
                var updatedTask = updatedTaskRequest.Task;
                updatedTask.AssignedUserId = updatedTaskRequest.User.UserId;

                var taskRequestsForTheSameTask = db.TaskRequests.Where(tr => tr.TaskId == updatedTaskRequest.TaskId && tr.TaskRequestId != updatedTaskRequest.TaskRequestId).ToList();
                taskRequestsForTheSameTask.ForEach(tr => tr.RequestStatusId = 26);
            }

            db.SaveChanges();
        }

        public void CreateTaskRequest(TaskRequestDTO taskRequest)
        {
            var taskRequestToBeInserted = new TaskRequest
            {
                TaskId = taskRequest.TaskId,
                UserId = taskRequest.UserId,
                RequestStatusId = 25
            };

            Db.TaskRequests.Add(taskRequestToBeInserted);
            Db.SaveChanges();
        }

        public IEnumerable<TaskRequestListViewModel> GetRequestsForTask(int taskId)
        {
            return Db.TaskRequests
                .Where(tr => tr.TaskId == taskId && tr.RequestStatus.Name == "Pending")
                .Select(tr => new TaskRequestListViewModel
                {
                    TaskRequestId = tr.TaskRequestId,
                    Name = tr.User.FullName,
                })
                .ToList();
        }

        public void DeleteTaskRequest(int taskId)
        {
            var jobRequest = Db.TaskRequests.Where(jr => jr.TaskRequestId == taskId).FirstOrDefault();

            Db.TaskRequests.Remove(jobRequest);
            Db.SaveChanges();
        }
    }
}