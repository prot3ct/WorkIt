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
        public DbSet<Job> Jobs { get; set; }
        public DbSet<Location> Locations { get; set; }
        public DbSet<Comment> Comments { get; set; }
        public DbSet<JobComments> JobComments { get; set; }
        public DbSet<UserReport> UserReports { get; set; }
        public DbSet<JobReport> TaskReports { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<JobComments>()
                .HasRequired(jc => jc.Comment)
                .WithMany()
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<JobComments>()
                .HasRequired(jc => jc.Job)
                .WithMany()
                .WillCascadeOnDelete(false);

            base.OnModelCreating(modelBuilder);
        }
    }
}