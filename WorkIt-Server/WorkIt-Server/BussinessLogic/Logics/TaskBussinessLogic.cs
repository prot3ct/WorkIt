using System;
using System.Collections.Generic;
using System.Data.Entity.Core.Objects;
using System.Data.Entity;
using System.Globalization;
using System.Linq;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class JobBussinessLogic
    {
        private WorkItDbContext db;

        public JobBussinessLogic(WorkItDbContext db)
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

        public void CreateTask(TaskDTO jobInformation)
        {
            var creator = Db.Users.Where(u => u.Email == jobInformation.CreatorEmail).FirstOrDefault();
            var jobToBeInserted = new Task
            {
                MinRaiting = int.Parse(jobInformation.MinRaiting),
                Reward = jobInformation.Reward,
                CreatorId = creator.UserId,
                Description = jobInformation.Description,
                StartDate = jobInformation.StartDate,
                Length = jobInformation.Length,
                Title = jobInformation.Title,
                Address = jobInformation.Address,
                City = jobInformation.City,
                IsCompleted = false,
                HasCreatorGivenRating = false,
                HasTaskerGivenRating = false,
                AssignedUserId = null
            };

            Db.Tasks.Add(jobToBeInserted);
            Db.SaveChanges();
        }

        public IEnumerable<TaskDTO> GetCompletedTasksByUser(int userId)
        {
            return Db.Tasks
                .Where(t => t.AssignedUserId == userId || t.CreatorId == userId)
                //.Where(t => EntityFunctions.CreateDateTime(t.EndDate.Year, t.EndDate.Month, t.EndDate.Day, t.EndDate.Hour, t.EndDate.Minute, 0) <= DateTime.Now)
                .Where(t => t.AssignedUserId == userId || t.CreatorId == userId)
                .Select(t => new TaskDTO
                {
                    Id = t.TaskId,
                    CreatorEmail = t.Creator.Email,
                    Address = t.Address,
                    City = t.City,
                    Description = t.Description,
                    StartDate = t.StartDate,
                    MinRaiting = t.MinRaiting.ToString(),
                    Reward = t.Reward,
                    Title = t.Title,
                })
                .ToList();
        }

        public IEnumerable<TaskDTO> GetAllAvailableTasks()
        {
            return Db.Tasks
                .Where(t => t.StartDate > DateTime.Now)
                .OrderBy(t => t.StartDate)
                .Select(j => new TaskDTO
                {
                    Id = j.TaskId,
                    CreatorEmail = j.Creator.Email,
                    Address = j.Address,
                    City = j.City,
                    Description = j.Description,
                    StartDate = j.StartDate,
                    MinRaiting = j.MinRaiting.ToString(),
                    Reward = j.Reward,
                    Title = j.Title
                })
                .ToList();
        }


        public IEnumerable<TaskDTO> GetTasksByUser(int userId)
        {
            return Db.Tasks
                .Where(t => t.Creator.UserId == userId)
                .Where(t => t.StartDate > DateTime.Now)
                .OrderBy(t => t.StartDate)
                .Select(j => new TaskDTO
                {
                    Id = j.TaskId,
                    CreatorEmail = j.Creator.Email,
                    Address = j.Address,
                    City = j.City,
                    Description = j.Description,
                    StartDate = j.StartDate,
                    MinRaiting = j.MinRaiting.ToString(),
                    Reward = j.Reward,
                    Title = j.Title
                })
                .ToList();
        }

        public void DeleteTaskById(int taskId)
        {
            var taskToBeDeleted = Db.Tasks.FirstOrDefault(t => t.TaskId == taskId);
            Db.Tasks.Remove(taskToBeDeleted);
            Db.SaveChanges();
        }

        public TaskDTO GetTaskById(int taskId)
        {
            var task = Db.Tasks.FirstOrDefault(t => t.TaskId == taskId);

            return new TaskDTO
            {
                Address = task.Address,
                City = task.City,
                Title = task.Title,
                Description = task.Description,
                CreatorEmail = task.Creator.Email,
                StartDate = task.StartDate,
                MinRaiting = task.MinRaiting.ToString(),
                Reward = task.Reward
            };
        }
    }
}