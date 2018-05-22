using System.Linq;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;
using WorkIt_Server.Models.ViewModels;

namespace WorkIt_Server.BLL
{
    public class AuthBussinessLogic
    {
        private WorkItDbContext db;

        public AuthBussinessLogic(WorkItDbContext db)
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

        public AuthBussinessLogic() { }

        public LoginViewModel getUserInfo(string email)
        {
            var user = db.Users.FirstOrDefault(u => u.Email == email);

            return new LoginViewModel
            {
                Email = user.Email,
                FullName = user.FullName,
                UserId = user.UserId
            };
        }

        public bool LoginUser(LoginDTO credentials)
        {
            var user = db.Users.FirstOrDefault(u => u.Email == credentials.Email && u.PassHash == credentials.PassHash);
            if (user != null)
            {
                return true;
            }
            return false;
        }

        public bool RegisterUser(RegisterDTO credentials)
        {
            if (db.Users.Select(u => u.Email).Contains(credentials.Email))
            {
                return false;
            }

            var userToBeInserted = new User
            {
                Email = credentials.Email,
                PassHash = credentials.PassHash,
                FullName = credentials.FullName,
                RaitingAsSupervisor = 3.00,
                RaitingAsTasker = 3.00,
                ReviewsAsSupervisor = 1,
                ReviewsAsTasker = 1
            };

            db.Users.Add(userToBeInserted);
            db.SaveChanges();
            return true;
        }
    }
}