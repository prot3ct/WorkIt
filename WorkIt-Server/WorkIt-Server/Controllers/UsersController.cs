using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WorkIt_Server.BLL;

namespace WorkIt_Server.Controllers
{
    [RoutePrefix("api")]
    public class UsersController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("users/{userId}")]
        [HttpGet]
        public IHttpActionResult GetProfileDetails(int userId)
        {
            try
            {
                return Ok(service.getProfileDetails(userId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
