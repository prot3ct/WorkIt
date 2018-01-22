using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class JobReportBussinessLogic
    {
        private WorkItDbContext db;

        public JobReportBussinessLogic(WorkItDbContext db)
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

        public bool CreateJobReport(JobReportDTO jobReport)
        {
            var jobReportToBeInserted = new JobReport
            {
                Description = jobReport.Description,
                JobId = jobReport.JobId,
                UserId = jobReport.UserId
            };

            db.JobReports.Add(jobReportToBeInserted);
            db.SaveChanges();
            return true;
        }
    }
}