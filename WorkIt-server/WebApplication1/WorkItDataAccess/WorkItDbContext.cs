namespace WorkItDataAccess
{
    using System;
    using System.Data.Entity;
    using System.Linq;
    using WorkItDataAccess.Models;

    public class WorkItDbContext : DbContext
    {
        public WorkItDbContext() : base()
        {
        }

        public DbSet<User> Users { get; set; }
        public DbSet<Job> Jobs { get; set; }

    }

}