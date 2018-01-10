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
using WorkIt_Server.BLL.Extensions;

namespace WorkIt_Server.Controllers
{
    [RoutePrefix("api")]
    public class JobsController : ApiController
    {
        private JobBussinessLogic jobsLogic = new JobBussinessLogic();
        private UserBussinessLogic userLogic = new UserBussinessLogic();
        private PlaceBussinessLogic placeLogic = new PlaceBussinessLogic();

        [Route("jobs")]
        [HttpGet]
        public IHttpActionResult GetAllJobs()
        {
            try
            {
                return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("jobs/create")]
        [HttpPost]
        public IHttpActionResult CreateJob(JobDTO jobDTO)
        {

                Job job = jobDTO.ToJob(userLogic, placeLogic);
                jobsLogic.AddJob(job);
                return Ok();

        }
    }
}
