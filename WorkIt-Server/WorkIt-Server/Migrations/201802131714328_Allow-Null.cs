namespace WorkIt_Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AllowNull : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.TaskComments", "TaskRequestId", "dbo.Tasks");
            DropIndex("dbo.TaskComments", new[] { "TaskRequestId" });
            AlterColumn("dbo.TaskComments", "TaskRequestId", c => c.Int());
            CreateIndex("dbo.TaskComments", "TaskRequestId");
            AddForeignKey("dbo.TaskComments", "TaskRequestId", "dbo.Tasks", "TaskId");
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.TaskComments", "TaskRequestId", "dbo.Tasks");
            DropIndex("dbo.TaskComments", new[] { "TaskRequestId" });
            AlterColumn("dbo.TaskComments", "TaskRequestId", c => c.Int(nullable: false));
            CreateIndex("dbo.TaskComments", "TaskRequestId");
            AddForeignKey("dbo.TaskComments", "TaskRequestId", "dbo.Tasks", "TaskId", cascadeDelete: true);
        }
    }
}
