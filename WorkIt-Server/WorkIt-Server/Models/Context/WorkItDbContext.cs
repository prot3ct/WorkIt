using System;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;

namespace WorkIt_Server.Models.Context
{
    public class WorkItDbContext : DbContext
    {
        public WorkItDbContext() : base("DefaultConnection")
        {
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Task> Tasks { get; set; }
        public DbSet<UserReport> UserReports { get; set; }
        public DbSet<TaskReport> TasksReports { get; set; }
        public DbSet<TaskRequest> TaskRequests { get; set; }
        public DbSet<Raiting> Raitings { get; set; }
        public DbSet<RequestStatus> RequestStatuses { get; set; }
        public DbSet<UserRole> UserRoles { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>().Property(m => m.Picture).IsOptional();

            base.OnModelCreating(modelBuilder);
        }
    }
}