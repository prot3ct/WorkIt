using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class UserBussinessLogic
    {
        public UserBussinessLogic() { }

        public bool LoginUser(WorkItDbContext db, LoginDTO credentials)
        {
            var user = db.Users.FirstOrDefault(u => u.Email == credentials.Email && u.PassHash == credentials.PassHash);
            if (user != null)
            {
                return true;
            }
            return false;
        }

        public bool RegisterUser(WorkItDbContext db, RegisterDTO credentials)
        {
            if (db.Users.Select(u => u.Email).Contains(credentials.Email))
            {
                return false;
            }

            var userToBeInserted = new User
            {
                Email = credentials.Email,
                PassHash = credentials.PassHash,
                Firstname = credentials.Firstname,
                Lastname = credentials.Lastname
            };

            db.Users.Add(userToBeInserted);
            db.SaveChanges();
            return true;
        }
    }
}