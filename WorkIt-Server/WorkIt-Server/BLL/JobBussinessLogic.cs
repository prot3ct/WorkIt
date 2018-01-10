using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;

namespace WorkIt_Server.BLL
{
    public class JobBussinessLogic : BussinessLogicBase
    {
        public JobBussinessLogic() { }

        public void AddJob(Job job)
        {
            WorkItDb.Jobs.Add(job);
            WorkItDb.SaveChanges();
        }
    }
}