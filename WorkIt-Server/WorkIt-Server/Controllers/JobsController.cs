using System;
using System.Collections.Generic;
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
    public class JobsController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("jobs")]
        [HttpGet]
        public IHttpActionResult GetAllJobs()
        {
            try
            {
                return Ok(service.GetAllJobs());
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("jobs/create")]
        [HttpPost]
        public IHttpActionResult CreateJob(JobDTO jobInformation)
        {
            try
            {
                if (service.CreateJob(jobInformation))
                {
                    return Ok();
                }
                return NotFound();
            }
            catch
            {
                return InternalServerError();
            }
        }
    }
}
