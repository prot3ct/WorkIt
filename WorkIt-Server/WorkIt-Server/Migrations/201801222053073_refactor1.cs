namespace WorkIt_Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class refactor1 : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.JobReports", "JobId", "dbo.Jobs");
            DropForeignKey("dbo.UserReports", "UserId", "dbo.Users");
            DropIndex("dbo.UserReports", new[] { "UserId" });
            CreateTable(
                "dbo.JobReports",
                c => new
                    {
                        JobReportId = c.Int(nullable: false, identity: true),
                        Description = c.String(nullable: false),
                        JobId = c.Int(nullable: false),
                        UserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.JobReportId)
                .ForeignKey("dbo.Jobs", t => t.JobId, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .Index(t => t.UserId);
            
            CreateTable(
                "dbo.JobRequests",
                c => new
                    {
                        JobRequestId = c.Int(nullable: false, identity: true),
                        Description = c.String(nullable: false),
                        UserId = c.Int(nullable: false),
                        JobId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.JobRequestId)
                .ForeignKey("dbo.Jobs", t => t.JobId, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .Index(t => t.UserId)
                .Index(t => t.JobId);
            
            CreateTable(
                "dbo.UserReports",
                c => new
                    {
                        UserReportId = c.Int(nullable: false, identity: true),
                        Description = c.String(nullable: false),
                        AuthorUserId = c.Int(nullable: false),
                        TargetUserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.UserReportId)
                .ForeignKey("dbo.Users", t => t.AuthorUserId, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.TargetUserId, cascadeDelete: true)
                .Index(t => t.AuthorUserId)
                .Index(t => t.TargetUserId);
            
            DropTable("dbo.JobReports");
            DropTable("dbo.UserReports");
        }
        
        public override void Down()
        {
            CreateTable(
                "dbo.UserReports",
                c => new
                    {
                        UserReportId = c.Int(nullable: false, identity: true),
                        Descriptin = c.String(nullable: false),
                        UserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.UserReportId);
            
            CreateTable(
                "dbo.JobReports",
                c => new
                    {
                        JobReportId = c.Int(nullable: false, identity: true),
                        Descriptin = c.String(nullable: false),
                        JobId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.JobReportId);
            
            DropForeignKey("dbo.UserReports", "TargetUserId", "dbo.Users");
            DropForeignKey("dbo.UserReports", "AuthorUserId", "dbo.Users");
            DropForeignKey("dbo.JobRequests", "UserId", "dbo.Users");
            DropForeignKey("dbo.JobRequests", "JobId", "dbo.Jobs");
            DropForeignKey("dbo.JobReports", "UserId", "dbo.Users");
            DropForeignKey("dbo.JobReports", "JobId", "dbo.Jobs");
            DropIndex("dbo.UserReports", new[] { "TargetUserId" });
            DropIndex("dbo.UserReports", new[] { "AuthorUserId" });
            DropIndex("dbo.JobRequests", new[] { "JobId" });
            DropIndex("dbo.JobRequests", new[] { "UserId" });
            DropIndex("dbo.JobReports", new[] { "UserId" });
            DropTable("dbo.UserReports");
            DropTable("dbo.JobRequests");
            DropTable("dbo.JobReports");
            CreateIndex("dbo.UserReports", "UserId");
            AddForeignKey("dbo.UserReports", "UserId", "dbo.Users", "UserId", cascadeDelete: true);
            AddForeignKey("dbo.JobReports", "JobId", "dbo.Jobs", "JobId", cascadeDelete: true);
        }
    }
}
