using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;

namespace WorkIt_Server.Controllers
{
    [RoutePrefix("api")]
    public class UsersController : ApiController
    {
        private WorkItDbContext db = new WorkItDbContext();

        [Route("auth/login")]
        [HttpPost]
        public IHttpActionResult Login(User credentials)
        {
            try
            {
                if (db.Users.Any(u => u.Email == credentials.Email && u.PassHash == credentials.PassHash))
                {
                    return Ok(db.Users.FirstOrDefault(u => u.Email == credentials.Email && u.PassHash == credentials.PassHash).Email);
                }
                return NotFound();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("auth/register")]
        [HttpPost]
        public IHttpActionResult Register(User user)
        {
            try
            {
                if (db.Users.Select(u => u.Email).Contains(user.Email))
                {
                    return NotFound();
                }

                db.Users.Add(user);
                db.SaveChanges();
                return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
