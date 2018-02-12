namespace WorkIt_Server.Migrations
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;
    using WorkIt_Server.Models;

    internal sealed class Configuration : DbMigrationsConfiguration<WorkIt_Server.Models.Context.WorkItDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        protected override void Seed(WorkIt_Server.Models.Context.WorkItDbContext context)
        {
            context.RequestStatuses.AddOrUpdate(
                new RequestStatus { Name = "Closed" },
                new RequestStatus { Name = "Open" }
            );

            context.UserRoles.AddOrUpdate(
                new UserRole { Name = "Employee" },
                new UserRole { Name = "Employer" }
            );

            context.SaveChanges();
        }
    }
}
