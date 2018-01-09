namespace WorkIt_Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Initial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Jobs",
                c => new
                    {
                        JobId = c.Int(nullable: false, identity: true),
                        Title = c.String(),
                        StarDate = c.DateTime(nullable: false),
                        EndDate = c.DateTime(nullable: false),
                        Description = c.String(),
                        Reward = c.String(),
                        minRaiting = c.Int(nullable: false),
                        minJobsCompleted = c.Int(nullable: false),
                        isComplted = c.Boolean(nullable: false),
                        CreatorId = c.Int(nullable: false),
                        Creator_UserId = c.Int(),
                    })
                .PrimaryKey(t => t.JobId)
                .ForeignKey("dbo.Users", t => t.Creator_UserId)
                .Index(t => t.Creator_UserId);
            
            CreateTable(
                "dbo.Users",
                c => new
                    {
                        UserId = c.Int(nullable: false, identity: true),
                        Email = c.String(),
                        PassHash = c.String(),
                        Firstname = c.String(),
                        Lastname = c.String(),
                        RaitingAsEmployee = c.Double(nullable: false),
                        RaitingAsCreator = c.Double(nullable: false),
                        JobsCompleted = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.UserId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Jobs", "Creator_UserId", "dbo.Users");
            DropIndex("dbo.Jobs", new[] { "Creator_UserId" });
            DropTable("dbo.Users");
            DropTable("dbo.Jobs");
        }
    }
}
