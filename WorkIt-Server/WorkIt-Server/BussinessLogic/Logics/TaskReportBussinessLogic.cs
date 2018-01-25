﻿using WorkIt_Server.Models;
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

        public void CreateTaskReport(TaskReportDTO jobReport)
        {
            var jobReportToBeInserted = new TaskReport
            {
                Description = jobReport.Description,
                TaskId = jobReport.TaskId,
                UserId = jobReport.UserId
            };

            Db.TasksReports.Add(jobReportToBeInserted);
            Db.SaveChanges();
        }
    }
}