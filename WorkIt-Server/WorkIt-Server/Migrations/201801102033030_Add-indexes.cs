namespace WorkIt_Server.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class Addindexes : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.Places", "Country", c => c.String(nullable: false, maxLength: 50));
            AlterColumn("dbo.Places", "City", c => c.String(nullable: false, maxLength: 50));
            AlterColumn("dbo.Places", "Address", c => c.String(nullable: false, maxLength: 50));
            CreateIndex("dbo.Places", new[] { "Country", "City", "Address" }, unique: true, name: "IX_CountryCityAndAddress");
        }
        
        public override void Down()
        {
            DropIndex("dbo.Places", "IX_CountryCityAndAddress");
            AlterColumn("dbo.Places", "Address", c => c.String(nullable: false));
            AlterColumn("dbo.Places", "City", c => c.String(nullable: false));
            AlterColumn("dbo.Places", "Country", c => c.String(nullable: false));
        }
    }
}
