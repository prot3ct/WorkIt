using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class UserReportBussinessLogic
    {
        private WorkItDbContext db;

        public UserReportBussinessLogic(WorkItDbContext db)
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

        //public void CreateUserReport(UserReportDTO userReport)
        //{
        //    var userReportTobeInserted = new UserReport
        //    {
        //        Description = userReport.Description,
        //        AuthorUserId = userReport.AuthorUserId,
        //        TargetUserId = userReport.TargetUserId
        //    };

        //    db.UserReports.Add(userReportTobeInserted);
        //    db.SaveChanges();
        //}
    }
}