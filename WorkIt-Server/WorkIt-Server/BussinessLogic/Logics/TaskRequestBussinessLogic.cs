using System;
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

        public TaskRequestDTO GetTaskRequestById(int requestId)
        {
            var taskRequest = db.TaskRequests.FirstOrDefault(tr => tr.TaskRequestId == requestId);
            var user = taskRequest.User;

            return new TaskRequestDTO
            {
                Description = taskRequest.Description,
                TaskRequestId = taskRequest.TaskRequestId,
                TaskTitle = taskRequest.Task.Title,
                Status = taskRequest.RequestStatus.Name,
                Name = user.Firstname + " " + user.Lastname
            };
        }

        public void CreateTaskRequest(TaskRequestDTO jobRequest)
        {
            var jobRequestToBeInserted = new TaskRequest
            {
                Description = jobRequest.Description,
                TaskId = jobRequest.TaskId,
                UserId = jobRequest.UserId,
                RequestStatusId = 1
            };

            Db.TaskRequests.Add(jobRequestToBeInserted);
            Db.SaveChanges();
        }

        public IEnumerable<TaskRequestDTO> GetRequestsForCurrentUser(int userId)
        {
            return Db.TaskRequests
                .Where(tr => tr.UserId == userId)
                .Select(tr => new TaskRequestDTO
                {
                    TaskRequestId = tr.TaskRequestId,
                    TaskTitle = tr.Task.Title,
                    Status = tr.RequestStatus.Name
                }).ToList();
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