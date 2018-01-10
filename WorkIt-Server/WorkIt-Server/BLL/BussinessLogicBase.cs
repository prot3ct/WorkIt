using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models.Context;

namespace WorkIt_Server.BLL
{
    public class BussinessLogicBase
    {
        private WorkItDbContext db;

        public BussinessLogicBase() {
            db = new WorkItDbContext();
        }

        protected WorkItDbContext WorkItDb { get; set; }
    }
}