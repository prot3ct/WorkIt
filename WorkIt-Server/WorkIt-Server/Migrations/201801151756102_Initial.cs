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
                        Title = c.String(nullable: false),
                        StartDate = c.DateTime(nullable: false),
                        EndDate = c.DateTime(nullable: false),
                        Description = c.String(nullable: false),
                        Reward = c.String(nullable: false),
                        MinRaiting = c.Int(nullable: false),
                        MinJobsCompleted = c.Int(nullable: false),
                        IsCompleted = c.Boolean(nullable: false),
                        LocationId = c.Int(nullable: false),
                        CreatorId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.JobId)
                .ForeignKey("dbo.Users", t => t.CreatorId, cascadeDelete: true)
                .ForeignKey("dbo.Locations", t => t.LocationId, cascadeDelete: true)
                .Index(t => t.LocationId)
                .Index(t => t.CreatorId);
            
            CreateTable(
                "dbo.Users",
                c => new
                    {
                        UserId = c.Int(nullable: false, identity: true),
                        Email = c.String(nullable: false),
                        PassHash = c.String(nullable: false),
                        Firstname = c.String(nullable: false),
                        Lastname = c.String(nullable: false),
                        RaitingAsEmployee = c.Double(nullable: true, defaultValue: 0),
                        RaitingAsCreator = c.Double(nullable: true, defaultValue: 0),
                        JobsCompleted = c.Int(nullable: true, defaultValue: 0),
                    })
                .PrimaryKey(t => t.UserId);
            
            CreateTable(
                "dbo.Locations",
                c => new
                    {
                        LocationId = c.Int(nullable: false, identity: true),
                        Country = c.String(nullable: false, maxLength: 50),
                        City = c.String(nullable: false, maxLength: 50),
                        Address = c.String(nullable: false, maxLength: 50),
                    })
                .PrimaryKey(t => t.LocationId)
                .Index(t => new { t.Country, t.City, t.Address }, unique: true, name: "IX_CountryCityAndAddress");
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Jobs", "LocationId", "dbo.Locations");
            DropForeignKey("dbo.Jobs", "CreatorId", "dbo.Users");
            DropIndex("dbo.Locations", "IX_CountryCityAndAddress");
            DropIndex("dbo.Jobs", new[] { "CreatorId" });
            DropIndex("dbo.Jobs", new[] { "LocationId" });
            DropTable("dbo.Locations");
            DropTable("dbo.Users");
            DropTable("dbo.Jobs");
        }
    }
}
