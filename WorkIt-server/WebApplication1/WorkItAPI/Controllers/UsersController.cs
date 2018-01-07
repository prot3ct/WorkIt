using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WorkItAPI.Models.Context;

namespace WorkItAPI.Controllers
{
    [RoutePrefix("api")]
    public class UsersController : ApiController
    {
        private WorkItDbContext db = new WorkItDbContext();

        [Route("auth/login")]
        [HttpPost]
        public IHttpActionResult Login()
        {
            try
            {
               // var user = db.Users.Where(u => u.Email = )
               return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

    }
}
