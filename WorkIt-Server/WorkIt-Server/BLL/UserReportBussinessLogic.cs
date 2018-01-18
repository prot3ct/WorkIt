using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class UserReportBussinessLogic
    {
        public bool CreateUserReport(WorkItDbContext db, UserReportDTO userReport)
        {
            var userReportTobeInserted = new UserReport
            {
                Descriptin = userReport.Description,
                AuthorUserId = userReport.AuthorUserId,
                TargetUserId = userReport.TargetUserId
            };

            db.UserReports.Add(userReportTobeInserted);
            db.SaveChanges();
            return true;
        }
    }
}