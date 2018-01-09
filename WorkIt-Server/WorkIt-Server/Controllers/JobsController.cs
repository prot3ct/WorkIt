using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WorkIt_Server.Models.Context;

namespace WorkIt_Server.Controllers
{
    [RoutePrefix("api")]
    public class JobsController : ApiController
    {
        private WorkItDbContext db = new WorkItDbContext();

        [Route("jobs")]
        [HttpGet]
        public IHttpActionResult GetAllJobs()
        {
            try
            {
                return Ok("CEKOOUPDATED123");
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("jobs/create")]
        [HttpPost]
        public IHttpActionResult CreateJob()
        {
            try
            {

                return Ok("CEKOO");
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
