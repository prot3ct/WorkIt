namespace WorkIt_Server.Migrations
{
    using System.Data.Entity.Migrations;
    using WorkIt_Server.Models;
    using WorkIt_Server.Models.Context;

    internal sealed class Configuration : DbMigrationsConfiguration<WorkIt_Server.Models.Context.WorkItDbContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        protected override void Seed(WorkItDbContext context)
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
