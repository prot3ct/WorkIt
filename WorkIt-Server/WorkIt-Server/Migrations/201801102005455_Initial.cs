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
                        StartDate = c.DateTime(nullable: false),
                        EndDate = c.DateTime(nullable: false),
                        Description = c.String(),
                        Reward = c.String(),
                        MinRaiting = c.Int(nullable: false),
                        MinJobsCompleted = c.Int(nullable: false),
                        IsCompleted = c.Boolean(nullable: false),
                        Creator_UserId = c.Int(),
                        Place_PlaceId = c.Int(),
                    })
                .PrimaryKey(t => t.JobId)
                .ForeignKey("dbo.Users", t => t.Creator_UserId)
                .ForeignKey("dbo.Places", t => t.Place_PlaceId)
                .Index(t => t.Creator_UserId)
                .Index(t => t.Place_PlaceId);
            
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
            
            CreateTable(
                "dbo.Places",
                c => new
                    {
                        PlaceId = c.Int(nullable: false, identity: true),
                        Country = c.String(),
                        City = c.String(),
                        Address = c.String(),
                    })
                .PrimaryKey(t => t.PlaceId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Jobs", "Place_PlaceId", "dbo.Places");
            DropForeignKey("dbo.Jobs", "Creator_UserId", "dbo.Users");
            DropIndex("dbo.Jobs", new[] { "Place_PlaceId" });
            DropIndex("dbo.Jobs", new[] { "Creator_UserId" });
            DropTable("dbo.Places");
            DropTable("dbo.Users");
            DropTable("dbo.Jobs");
        }
    }
}
