using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models.Context;

namespace WorkIt_Server.BLL
{
    public class BussinessLogicBase
    {
        protected WorkItDbContext WorkItDb = new WorkItDbContext();
    }
}