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
    public class JobsController : ApiController
    {
        private WorkItDbContext db = new WorkItDbContext();

        [Route("jobs")]
        [HttpGet]
        public IHttpActionResult GetAllJobs()
        {
            try
            {
                var result = db.Jobs.ToList();
                return Ok(result);
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
                //var content = Request.Content.ReadAsStringAsync();
                //string body = content.Result;
               
                var result = db.Jobs.ToList();
                return Ok(result);
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
