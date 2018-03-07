using System;
using System.Collections.Generic;
using System.Data.Entity.Core.Objects;
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

        public void CreateTask(TaskDTO jobInformation, int locationId)
        {
            var creator = Db.Users.Where(u => u.Email == jobInformation.CreatorEmail).FirstOrDefault();

            var jobToBeInserted = new Task
            {
                MinTasksCompleted = int.Parse(jobInformation.MinTasksCompleted),
                MinRaiting = int.Parse(jobInformation.MinRaiting),
                Reward = jobInformation.Reward,
                CreatorId = creator.UserId,
                Description = jobInformation.Description,
                StartDate = jobInformation.StartDate,
                EndDate = jobInformation.EndDate,
                LocationId = locationId,
                Title = jobInformation.Title,
                IsCompleted = false,
                HasCreatorGivenRating = false,
                HasTaskterGivenRating = false,
                AssignedUserId = null
            };

            Db.Tasks.Add(jobToBeInserted);
            Db.SaveChanges();
        }

        public IEnumerable<TaskDTO> GetCompletedTasksByUser(int userId)
        {
            return Db.Tasks
                .Where(t => t.AssignedUserId == userId || t.CreatorId == userId)
                .Where(t => EntityFunctions.CreateDateTime(t.EndDate.Year, t.EndDate.Month, t.EndDate.Day, t.EndDate.Hour, t.EndDate.Minute, 0) >= DateTime.Now)
                .Select(t => new TaskDTO
                {
                    Id = t.TaskId,
                    CreatorEmail = t.Creator.Email,
                    Address = t.Location.Address,
                    City = t.Location.City,
                    Country = t.Location.Country,
                    Description = t.Description,
                    EndDate = t.EndDate,
                    StartDate = t.StartDate,
                    MinTasksCompleted = t.MinTasksCompleted.ToString(),
                    MinRaiting = t.MinRaiting.ToString(),
                    Reward = t.Reward,
                    Title = t.Title
                })
                .ToList();
        }

        public IEnumerable<TaskDTO> GetAllTasks()
        {
            return Db.Tasks.Select(j => new TaskDTO
            {
                Id = j.TaskId,
                CreatorEmail = j.Creator.Email,
                Address = j.Location.Address,
                City = j.Location.City,
                Country = j.Location.Country,
                Description = j.Description,
                EndDate = j.EndDate,
                StartDate = j.StartDate,
                MinTasksCompleted = j.MinTasksCompleted.ToString(),
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
                .Select(j => new TaskDTO
                {
                    Id = j.TaskId,
                    CreatorEmail = j.Creator.Email,
                    Address = j.Location.Address,
                    City = j.Location.City,
                    Country = j.Location.Country,
                    Description = j.Description,
                    EndDate = j.EndDate,
                    StartDate = j.StartDate,
                    MinTasksCompleted = j.MinTasksCompleted.ToString(),
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
                Address = task.Location.Address,
                City = task.Location.City,
                Country = task.Location.Country,
                Title = task.Title,
                Description = task.Description,
                CreatorEmail = task.Creator.Email,
                MinTasksCompleted = task.MinTasksCompleted.ToString(),
                EndDate = task.EndDate,
                StartDate = task.StartDate,
                MinRaiting = task.MinRaiting.ToString(),
                Reward = task.Reward
            };
        }
    }
}