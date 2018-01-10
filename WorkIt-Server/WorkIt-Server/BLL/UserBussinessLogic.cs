using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;

namespace WorkIt_Server.BLL
{
    public class UserBussinessLogic : BussinessLogicBase
    {
        public UserBussinessLogic() { }

        public User GetUserByEmail(string email)
        {
            var reuslt = base.WorkItDb.Users.Where(u => u.Email == email).FirstOrDefault();

            return reuslt;
        }
    }
}