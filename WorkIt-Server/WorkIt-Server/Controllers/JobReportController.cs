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
    public class TaskReportController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("jobs/reports")]
        [HttpGet]
        public IHttpActionResult GetCommectsForJobById(int id)
        {
            try
            {
                return Ok(service.GetCommentsById(id));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
