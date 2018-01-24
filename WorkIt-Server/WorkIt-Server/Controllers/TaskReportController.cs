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
    public class JobReportController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("jobs/reports")]
        [HttpPost]
        public IHttpActionResult GetCommectsForJobById(TaskReportDTO jobReport)
        {
            try
            {
                return Ok(service.PostJobReport(jobReport));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
