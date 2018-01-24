namespace WorkIt_Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Initial : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Jobs", "CreatorId", "dbo.Users");
            DropForeignKey("dbo.Jobs", "LocationId", "dbo.Locations");
            DropForeignKey("dbo.JobReports", "JobId", "dbo.Jobs");
            DropForeignKey("dbo.JobComments", "CommentId", "dbo.Comments");
            DropForeignKey("dbo.JobComments", "JobId", "dbo.Jobs");
            DropForeignKey("dbo.UserReports", "UserId", "dbo.Users");
            DropIndex("dbo.Jobs", new[] { "LocationId" });
            DropIndex("dbo.Jobs", new[] { "CreatorId" });
            DropIndex("dbo.JobReports", new[] { "JobId" });
            DropIndex("dbo.JobComments", new[] { "JobId" });
            DropIndex("dbo.JobComments", new[] { "CommentId" });
            DropIndex("dbo.UserReports", new[] { "UserId" });
            CreateTable(
                "dbo.Tasks",
                c => new
                    {
                        TaskId = c.Int(nullable: false, identity: true),
                        Title = c.String(nullable: false),
                        StartDate = c.DateTime(nullable: false),
                        EndDate = c.DateTime(nullable: false),
                        Description = c.String(nullable: false),
                        Reward = c.String(nullable: false),
                        MinRaiting = c.Int(nullable: false),
                        MinTasksCompleted = c.Int(nullable: false),
                        IsCompleted = c.Boolean(nullable: false),
                        LocationId = c.Int(nullable: false),
                        CreatorId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.TaskId)
                .ForeignKey("dbo.Users", t => t.CreatorId, cascadeDelete: true)
                .ForeignKey("dbo.Locations", t => t.LocationId, cascadeDelete: true)
                .Index(t => t.LocationId)
                .Index(t => t.CreatorId);
            
            CreateTable(
                "dbo.Raitings",
                c => new
                    {
                        RaitingId = c.Int(nullable: false, identity: true),
                        GiverUserId = c.Int(nullable: false),
                        ReceiverUserId = c.Int(nullable: false),
                        TaskId = c.Int(nullable: false),
                        ReceiverUserRoleId = c.Int(nullable: false),
                        Value = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.RaitingId)
                .ForeignKey("dbo.Users", t => t.GiverUserId, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.ReceiverUserId, cascadeDelete: true)
                .ForeignKey("dbo.UserRoles", t => t.ReceiverUserRoleId, cascadeDelete: true)
                .ForeignKey("dbo.Tasks", t => t.TaskId, cascadeDelete: true)
                .Index(t => t.GiverUserId)
                .Index(t => t.ReceiverUserId)
                .Index(t => t.TaskId)
                .Index(t => t.ReceiverUserRoleId);
            
            CreateTable(
                "dbo.UserRoles",
                c => new
                    {
                        UserRoleId = c.Int(nullable: false, identity: true),
                        Name = c.String(nullable: false),
                    })
                .PrimaryKey(t => t.UserRoleId);
            
            CreateTable(
                "dbo.TaskRequests",
                c => new
                    {
                        TaskRequestId = c.Int(nullable: false, identity: true),
                        Description = c.String(nullable: false),
                        UserId = c.Int(nullable: false),
                        TaskId = c.Int(nullable: false),
                        RequestStatusId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.TaskRequestId)
                .ForeignKey("dbo.RequestStatus", t => t.RequestStatusId, cascadeDelete: true)
                .ForeignKey("dbo.Tasks", t => t.TaskId, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .Index(t => t.UserId)
                .Index(t => t.TaskId)
                .Index(t => t.RequestStatusId);
            
            CreateTable(
                "dbo.RequestStatus",
                c => new
                    {
                        RequestStatusId = c.Int(nullable: false, identity: true),
                        Name = c.String(nullable: false),
                    })
                .PrimaryKey(t => t.RequestStatusId);
            
            CreateTable(
                "dbo.TaskComments",
                c => new
                    {
                        TaskCommentsId = c.Int(nullable: false, identity: true),
                        TaskId = c.Int(nullable: false),
                        CommentId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.TaskCommentsId)
                .ForeignKey("dbo.Comments", t => t.CommentId)
                .ForeignKey("dbo.Tasks", t => t.TaskId)
                .Index(t => t.TaskId)
                .Index(t => t.CommentId);
            
            CreateTable(
                "dbo.TaskReports",
                c => new
                    {
                        TaskReportId = c.Int(nullable: false, identity: true),
                        Description = c.String(nullable: false),
                        TaskId = c.Int(nullable: false),
                        UserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.TaskReportId)
                .ForeignKey("dbo.Tasks", t => t.TaskId, cascadeDelete: true)
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .Index(t => t.TaskId)
                .Index(t => t.UserId);
            
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
            
            AddColumn("dbo.Comments", "TaskId", c => c.Int(nullable: false));
            AddColumn("dbo.Users", "TaskCompleted", c => c.Int(nullable: false));
            CreateIndex("dbo.Comments", "TaskId");
            AddForeignKey("dbo.Comments", "TaskId", "dbo.Tasks", "TaskId", cascadeDelete: true);
            DropColumn("dbo.Users", "JobsCompleted");
            DropTable("dbo.Jobs");
            DropTable("dbo.JobReports");
            DropTable("dbo.JobComments");
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
                "dbo.JobComments",
                c => new
                    {
                        JobCommentsId = c.Int(nullable: false, identity: true),
                        JobId = c.Int(nullable: false),
                        CommentId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.JobCommentsId);
            
            CreateTable(
                "dbo.JobReports",
                c => new
                    {
                        JobReportId = c.Int(nullable: false, identity: true),
                        Descriptin = c.String(nullable: false),
                        JobId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.JobReportId);
            
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
                .PrimaryKey(t => t.JobId);
            
            AddColumn("dbo.Users", "JobsCompleted", c => c.Int(nullable: false));
            DropForeignKey("dbo.UserReports", "TargetUserId", "dbo.Users");
            DropForeignKey("dbo.UserReports", "AuthorUserId", "dbo.Users");
            DropForeignKey("dbo.TaskReports", "UserId", "dbo.Users");
            DropForeignKey("dbo.TaskReports", "TaskId", "dbo.Tasks");
            DropForeignKey("dbo.TaskComments", "TaskId", "dbo.Tasks");
            DropForeignKey("dbo.TaskComments", "CommentId", "dbo.Comments");
            DropForeignKey("dbo.TaskRequests", "UserId", "dbo.Users");
            DropForeignKey("dbo.TaskRequests", "TaskId", "dbo.Tasks");
            DropForeignKey("dbo.TaskRequests", "RequestStatusId", "dbo.RequestStatus");
            DropForeignKey("dbo.Raitings", "TaskId", "dbo.Tasks");
            DropForeignKey("dbo.Raitings", "ReceiverUserRoleId", "dbo.UserRoles");
            DropForeignKey("dbo.Raitings", "ReceiverUserId", "dbo.Users");
            DropForeignKey("dbo.Raitings", "GiverUserId", "dbo.Users");
            DropForeignKey("dbo.Comments", "TaskId", "dbo.Tasks");
            DropForeignKey("dbo.Tasks", "LocationId", "dbo.Locations");
            DropForeignKey("dbo.Tasks", "CreatorId", "dbo.Users");
            DropIndex("dbo.UserReports", new[] { "TargetUserId" });
            DropIndex("dbo.UserReports", new[] { "AuthorUserId" });
            DropIndex("dbo.TaskReports", new[] { "UserId" });
            DropIndex("dbo.TaskReports", new[] { "TaskId" });
            DropIndex("dbo.TaskComments", new[] { "CommentId" });
            DropIndex("dbo.TaskComments", new[] { "TaskId" });
            DropIndex("dbo.TaskRequests", new[] { "RequestStatusId" });
            DropIndex("dbo.TaskRequests", new[] { "TaskId" });
            DropIndex("dbo.TaskRequests", new[] { "UserId" });
            DropIndex("dbo.Raitings", new[] { "ReceiverUserRoleId" });
            DropIndex("dbo.Raitings", new[] { "TaskId" });
            DropIndex("dbo.Raitings", new[] { "ReceiverUserId" });
            DropIndex("dbo.Raitings", new[] { "GiverUserId" });
            DropIndex("dbo.Tasks", new[] { "CreatorId" });
            DropIndex("dbo.Tasks", new[] { "LocationId" });
            DropIndex("dbo.Comments", new[] { "TaskId" });
            DropColumn("dbo.Users", "TaskCompleted");
            DropColumn("dbo.Comments", "TaskId");
            DropTable("dbo.UserReports");
            DropTable("dbo.TaskReports");
            DropTable("dbo.TaskComments");
            DropTable("dbo.RequestStatus");
            DropTable("dbo.TaskRequests");
            DropTable("dbo.UserRoles");
            DropTable("dbo.Raitings");
            DropTable("dbo.Tasks");
            CreateIndex("dbo.UserReports", "UserId");
            CreateIndex("dbo.JobComments", "CommentId");
            CreateIndex("dbo.JobComments", "JobId");
            CreateIndex("dbo.JobReports", "JobId");
            CreateIndex("dbo.Jobs", "CreatorId");
            CreateIndex("dbo.Jobs", "LocationId");
            AddForeignKey("dbo.UserReports", "UserId", "dbo.Users", "UserId", cascadeDelete: true);
            AddForeignKey("dbo.JobComments", "JobId", "dbo.Jobs", "JobId");
            AddForeignKey("dbo.JobComments", "CommentId", "dbo.Comments", "CommentId");
            AddForeignKey("dbo.JobReports", "JobId", "dbo.Jobs", "JobId", cascadeDelete: true);
            AddForeignKey("dbo.Jobs", "LocationId", "dbo.Locations", "LocationId", cascadeDelete: true);
            AddForeignKey("dbo.Jobs", "CreatorId", "dbo.Users", "UserId", cascadeDelete: true);
        }
    }
}
