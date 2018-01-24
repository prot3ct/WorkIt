using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.Context
{
    public class WorkItDbContext : DbContext
    {
        public WorkItDbContext() : base("DefaultConnection")
        {
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Task> Tasks { get; set; }
        public DbSet<Location> Locations { get; set; }
        public DbSet<Comment> Comments { get; set; }
        public DbSet<TaskComments> TasksComments { get; set; }
        public DbSet<UserReport> UserReports { get; set; }
        public DbSet<TaskReport> TasksReports { get; set; }
        public DbSet<TaskRequest> TaskRequests { get; set; }
        public DbSet<Raiting> Raitings { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
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