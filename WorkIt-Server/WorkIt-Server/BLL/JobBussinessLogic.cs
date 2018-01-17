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
        public JobBussinessLogic() { }

        public bool CreateJob(WorkItDbContext db, JobDTO jobInformation, int locationId)
        {
            var creator = db.Users.Where(u => u.Email == jobInformation.CreatorEmail).FirstOrDefault();

            var jobToBeInserted = new Job
            {
                MinJobsCompleted = jobInformation.MinJobsCompleted,
                MinRaiting = jobInformation.MinRaiting,
                Reward = jobInformation.Reward,
                Creator = creator,
                Description = jobInformation.Description,
                StartDate = jobInformation.StartDate,
                EndDate = jobInformation.EndDate,
                LocationId = locationId,
                Title = jobInformation.Title,
            };

            db.Jobs.Add(jobToBeInserted);
            db.SaveChanges();
            return true;
        }

        public IEnumerable<JobDTO> GetAllJobs(WorkItDbContext db)
        {
            return db.Jobs.ToList().Select(j => new JobDTO
            {
                CreatorEmail = j.Creator.Email,
                Address = j.Location.Address,
                City = j.Location.City,
                Country = j.Location.Country,
                Description = j.Description,
                EndDate = j.EndDate,
                StartDate = j.StartDate,
                MinJobsCompleted = j.MinJobsCompleted,
                MinRaiting = j.MinRaiting,
                Reward = j.Reward,
                Title = j.Title
            })
            .ToList();
        }
    }
}