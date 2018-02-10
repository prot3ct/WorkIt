using System.Linq;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.BLL
{
    public class UserBussinessLogic
    {
        private WorkItDbContext db;

        public UserBussinessLogic(WorkItDbContext db)
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

        public UserBussinessLogic() { }

        public int GetUserIdByEmail(string email)
        {
            return db.Users.FirstOrDefault(u => u.Email == email).UserId;
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
                Firstname = credentials.Firstname,
                Lastname = credentials.Lastname
            };

            db.Users.Add(userToBeInserted);
            db.SaveChanges();
            return true;
        }
    }
}