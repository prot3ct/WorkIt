using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WorkIt_Server.BLL;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.Controllers
{
    [RoutePrefix("api")]
    public class JobRequestController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("jobs/requests")]
        [HttpPost]
        public IHttpActionResult GetCommectsForJobById(TaskRequestDTO jobRequest)
        {
            try
            {
                return Ok(service.CreateJobRequest(jobRequest));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
