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

        public TaskRequestDetailsViewModel GetTaskRequestById(int requestId)
        {
            var taskRequest = db.TaskRequests.FirstOrDefault(tr => tr.TaskRequestId == requestId);
            var user = taskRequest.User;

            return new TaskRequestDetailsViewModel
            {
                Description = taskRequest.Description,
                TaskRequestId = taskRequest.TaskRequestId,
                TaskTitle = taskRequest.Task.Title,
                Status = taskRequest.RequestStatus.Name,
                Name = user.Firstname + " " + user.Lastname
            };
        }

        public void UpdateTaskRequest(TaskRequestDTO taskRequest)
        {
            var updatedTaskRequest = db.TaskRequests.FirstOrDefault(tr => tr.TaskRequestId == taskRequest.TaskRequestId);
            updatedTaskRequest.RequestStatusId = taskRequest.RequestStatusId;

            if (updatedTaskRequest.RequestStatusId == 2)
            {
                var updatedTask = updatedTaskRequest.Task;
                updatedTask.AssignedUserId = updatedTaskRequest.User.UserId;

                var taskRequestsForTheSameTask = db.TaskRequests.Where(tr => tr.TaskId == updatedTaskRequest.TaskId).ToList();
                taskRequestsForTheSameTask.ForEach(tr => tr.RequestStatusId = 1);
            }

            db.SaveChanges();
        }

        public void CreateTaskRequest(TaskRequestDTO taskRequest)
        {
            var taskRequestToBeInserted = new TaskRequest
            {
                Description = taskRequest.Description,
                TaskId = taskRequest.TaskId,
                UserId = taskRequest.UserId,
                RequestStatusId = 1
            };

            Db.TaskRequests.Add(taskRequestToBeInserted);
            Db.SaveChanges();
        }

        public IEnumerable<TaskRequestListViewModel> GetRequestsForTask(int taskId)
        {
            return Db.TaskRequests
                .Where(tr => tr.TaskId == taskId)
                .Select(tr => new TaskRequestListViewModel
                {
                    TaskRequestId = tr.TaskRequestId,
                    Name = tr.User.Firstname + " " + tr.User.Lastname,
                    Status = tr.RequestStatus.Name
                })
                .ToList();
        }

        public void CreateTaskRequestComment(CommentDTO comment)
        {
            var commentToBeInserted = new Comment
            {
                Body = comment.Body,
                UserId = comment.UserId
            };

            db.Comments.Add(commentToBeInserted);
            db.SaveChanges();

            var commentRelation = new TaskComments
            {
                CommentId = commentToBeInserted.CommentId,
                TaskRequestId = comment.TargetId
            };

            db.TasksComments.Add(commentRelation);
            db.SaveChanges();
        }

        public List<CommentDTO> GetTaskRequestComments(int taskRequestId)
        {
            var commentIds = db.TasksComments
                .Where(tc => tc.TaskRequestId == taskRequestId)
                .Select(tc => tc.CommentId)
                .ToList();

            return db.Comments
                .Where(c => commentIds.Contains(c.CommentId))
                .Select(c => new CommentDTO
                {
                    Body = c.Body,
                    Name = c.User.Firstname + " " + c.User.Lastname
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