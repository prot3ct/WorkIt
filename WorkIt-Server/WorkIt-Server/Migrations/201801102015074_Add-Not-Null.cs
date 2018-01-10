namespace WorkIt_Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class AddNotNull : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Jobs", "Creator_UserId", "dbo.Users");
            DropForeignKey("dbo.Jobs", "Place_PlaceId", "dbo.Places");
            DropIndex("dbo.Jobs", new[] { "Creator_UserId" });
            DropIndex("dbo.Jobs", new[] { "Place_PlaceId" });
            AlterColumn("dbo.Jobs", "Title", c => c.String(nullable: false));
            AlterColumn("dbo.Jobs", "Description", c => c.String(nullable: false));
            AlterColumn("dbo.Jobs", "Reward", c => c.String(nullable: false));
            AlterColumn("dbo.Jobs", "Creator_UserId", c => c.Int(nullable: false));
            AlterColumn("dbo.Jobs", "Place_PlaceId", c => c.Int(nullable: false));
            AlterColumn("dbo.Users", "Email", c => c.String(nullable: false));
            AlterColumn("dbo.Users", "PassHash", c => c.String(nullable: false));
            AlterColumn("dbo.Users", "Firstname", c => c.String(nullable: false));
            AlterColumn("dbo.Users", "Lastname", c => c.String(nullable: false));
            AlterColumn("dbo.Places", "Country", c => c.String(nullable: false));
            AlterColumn("dbo.Places", "City", c => c.String(nullable: false));
            AlterColumn("dbo.Places", "Address", c => c.String(nullable: false));
            CreateIndex("dbo.Jobs", "Creator_UserId");
            CreateIndex("dbo.Jobs", "Place_PlaceId");
            AddForeignKey("dbo.Jobs", "Creator_UserId", "dbo.Users", "UserId", cascadeDelete: true);
            AddForeignKey("dbo.Jobs", "Place_PlaceId", "dbo.Places", "PlaceId", cascadeDelete: true);
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Jobs", "Place_PlaceId", "dbo.Places");
            DropForeignKey("dbo.Jobs", "Creator_UserId", "dbo.Users");
            DropIndex("dbo.Jobs", new[] { "Place_PlaceId" });
            DropIndex("dbo.Jobs", new[] { "Creator_UserId" });
            AlterColumn("dbo.Places", "Address", c => c.String());
            AlterColumn("dbo.Places", "City", c => c.String());
            AlterColumn("dbo.Places", "Country", c => c.String());
            AlterColumn("dbo.Users", "Lastname", c => c.String());
            AlterColumn("dbo.Users", "Firstname", c => c.String());
            AlterColumn("dbo.Users", "PassHash", c => c.String());
            AlterColumn("dbo.Users", "Email", c => c.String());
            AlterColumn("dbo.Jobs", "Place_PlaceId", c => c.Int());
            AlterColumn("dbo.Jobs", "Creator_UserId", c => c.Int());
            AlterColumn("dbo.Jobs", "Reward", c => c.String());
            AlterColumn("dbo.Jobs", "Description", c => c.String());
            AlterColumn("dbo.Jobs", "Title", c => c.String());
            CreateIndex("dbo.Jobs", "Place_PlaceId");
            CreateIndex("dbo.Jobs", "Creator_UserId");
            AddForeignKey("dbo.Jobs", "Place_PlaceId", "dbo.Places", "PlaceId");
            AddForeignKey("dbo.Jobs", "Creator_UserId", "dbo.Users", "UserId");
        }
    }
}
