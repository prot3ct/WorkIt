using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;

namespace WorkIt_Server.BussinessLogic.Logics
{
    public class RaitingBussinessLogic
    {
        private WorkItDbContext db;

        public RaitingBussinessLogic(WorkItDbContext db)
        {
            this.Db = db;
        }

        public WorkItDbContext Db
        {
            get
            {
                return this.db;
            }
            set
            {
                this.db = value;
            }
        }

        public void CreateRatiing(Raiting raiting)
        {
            db.Raitings.Add(raiting);
            db.SaveChanges();
        }
    }
}