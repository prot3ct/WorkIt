using System.Collections.Generic;
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
                MinTasksCompleted = jobInformation.MinTasksCompleted,
                MinRaiting = jobInformation.MinRaiting,
                Reward = jobInformation.Reward,
                CreatorId = creator.UserId,
                Description = jobInformation.Description,
                StartDate = jobInformation.StartDate,
                EndDate = jobInformation.EndDate,
                LocationId = locationId,
                Title = jobInformation.Title
            };

            Db.Tasks.Add(jobToBeInserted);
            Db.SaveChanges();
        }

        public IEnumerable<TaskDTO> GetAllTasks()
        {
            return Db.Tasks.Select(j => new TaskDTO
            {
                CreatorEmail = j.Creator.Email,
                Address = j.Location.Address,
                City = j.Location.City,
                Country = j.Location.Country,
                Description = j.Description,
                EndDate = j.EndDate,
                StartDate = j.StartDate,
                MinTasksCompleted = j.MinTasksCompleted,
                MinRaiting = j.MinRaiting,
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
                MinTasksCompleted = task.MinTasksCompleted,
                EndDate = task.EndDate,
                StartDate = task.StartDate,
                MinRaiting = task.MinRaiting,
                Reward = task.Reward
            };
        }
    }
}