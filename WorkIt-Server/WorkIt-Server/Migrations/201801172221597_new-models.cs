namespace WorkIt_Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class newmodels : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Comments",
                c => new
                    {
                        CommentId = c.Int(nullable: false, identity: true),
                        Message = c.String(nullable: false),
                        AuthorId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.CommentId)
                .ForeignKey("dbo.Users", t => t.AuthorId, cascadeDelete: true)
                .Index(t => t.AuthorId);
            
            CreateTable(
                "dbo.JobReports",
                c => new
                    {
                        JobReportId = c.Int(nullable: false, identity: true),
                        Descriptin = c.String(nullable: false),
                        JobId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.JobReportId)
                .ForeignKey("dbo.Jobs", t => t.JobId, cascadeDelete: true)
                .Index(t => t.JobId);
            
            CreateTable(
                "dbo.JobComments",
                c => new
                    {
                        JobCommentsId = c.Int(nullable: false, identity: true),
                        JobId = c.Int(nullable: false),
                        CommentId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.JobCommentsId)
                .ForeignKey("dbo.Comments", t => t.CommentId)
                .ForeignKey("dbo.Jobs", t => t.JobId)
                .Index(t => t.JobId)
                .Index(t => t.CommentId);
            
            CreateTable(
                "dbo.UserReports",
                c => new
                    {
                        UserReportId = c.Int(nullable: false, identity: true),
                        Descriptin = c.String(nullable: false),
                        UserId = c.Int(nullable: false),
                    })
                .PrimaryKey(t => t.UserReportId)
                .ForeignKey("dbo.Users", t => t.UserId, cascadeDelete: true)
                .Index(t => t.UserId);
            
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.UserReports", "UserId", "dbo.Users");
            DropForeignKey("dbo.JobComments", "JobId", "dbo.Jobs");
            DropForeignKey("dbo.JobComments", "CommentId", "dbo.Comments");
            DropForeignKey("dbo.JobReports", "JobId", "dbo.Jobs");
            DropForeignKey("dbo.Comments", "AuthorId", "dbo.Users");
            DropIndex("dbo.UserReports", new[] { "UserId" });
            DropIndex("dbo.JobComments", new[] { "CommentId" });
            DropIndex("dbo.JobComments", new[] { "JobId" });
            DropIndex("dbo.JobReports", new[] { "JobId" });
            DropIndex("dbo.Comments", new[] { "AuthorId" });
            DropTable("dbo.UserReports");
            DropTable("dbo.JobComments");
            DropTable("dbo.JobReports");
            DropTable("dbo.Comments");
        }
    }
}
