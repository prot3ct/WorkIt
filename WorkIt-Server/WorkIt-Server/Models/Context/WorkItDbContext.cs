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
        public DbSet<Comment> Comments { get; set; }
        public DbSet<TaskComments> TasksComments { get; set; }
        public DbSet<UserReport> UserReports { get; set; }
        public DbSet<TaskReport> TasksReports { get; set; }
        public DbSet<TaskRequest> TaskRequests { get; set; }
        public DbSet<Raiting> Raitings { get; set; }
        public DbSet<RequestStatus> RequestStatuses { get; set; }
        public DbSet<UserRole> UserRoles { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            //modelBuilder.Entity<Task>()
            //    .HasRequired(t => t.Creator)
            //    .WithMany()
            //    .WillCascadeOnDelete(false);

            //modelBuilder.Entity<User>()
            //    .HasRequired(u => u.Tasks)
            //    .WithMany()
            //    .WillCascadeOnDelete(false);

            modelBuilder.Entity<TaskComments>()
                .HasRequired(jc => jc.Comment)
                .WithMany()
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<TaskComments>()
                .HasRequired(jc => jc.Task)
                .WithMany()
                .WillCascadeOnDelete(false);


            base.OnModelCreating(modelBuilder);
        }
    }
}