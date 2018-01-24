using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
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

        public bool CreateJob(TaskDTO jobInformation, int locationId)
        {
            var creator = db.Users.Where(u => u.Email == jobInformation.CreatorEmail).FirstOrDefault();

            var jobToBeInserted = new Task
            {
                MinJobsCompleted = jobInformation.MinTasksCompleted,
                MinRaiting = jobInformation.MinRaiting,
                Reward = jobInformation.Reward,
                Creator = creator,
                Description = jobInformation.Description,
                StartDate = jobInformation.StartDate,
                EndDate = jobInformation.EndDate,
                LocationId = locationId,
                Title = jobInformation.Title,
            };

            db.Tasks.Add(jobToBeInserted);
            db.SaveChanges();
            return true;
        }

        public IEnumerable<TaskDTO> GetAllJobs()
        {
            return db.Tasks.ToList().Select(j => new TaskDTO
            {
                CreatorEmail = j.Creator.Email,
                Address = j.Location.Address,
                City = j.Location.City,
                Country = j.Location.Country,
                Description = j.Description,
                EndDate = j.EndDate,
                StartDate = j.StartDate,
                MinTasksCompleted = j.MinJobsCompleted,
                MinRaiting = j.MinRaiting,
                Reward = j.Reward,
                Title = j.Title
            })
            .ToList();
        }
    }
}