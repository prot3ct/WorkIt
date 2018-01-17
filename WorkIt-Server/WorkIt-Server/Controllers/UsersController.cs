using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Data.Entity.Validation;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WorkIt_Server.BLL;
using WorkIt_Server.Models;
using WorkIt_Server.Models.Context;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.Controllers
{
    [RoutePrefix("api")]
    public class UsersController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("auth/login")]
        [HttpPost]
        public IHttpActionResult Login(LoginDTO credentials)
        {
            try
            {
                if (service.LoginUser(credentials))
                {
                    return Ok(credentials.Email);
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
        public IHttpActionResult Register(RegisterDTO credentials)
        {
            try
            {
                if (service.RegisterUser(credentials))
                {
                    return Ok();
                }
                return NotFound();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
