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
    public class TasksController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("tasks")]
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

        [Route("tasks/create")]
        [HttpPost]
        public IHttpActionResult CreateTask(TaskDTO jobInformation)
        {
            try
            {
                service.CreateTask(jobInformation);
                return Ok();
            }
            catch
            {
                return InternalServerError();
            }
        }

        [Route("tasks/{taskId}")]
        [HttpGet]
        public IHttpActionResult GetTaskById(int taskId)
        {
            try
            {
                return Ok(service.GetTaskById(taskId));
            }
            catch
            {
                return InternalServerError();
            }
        }

        [Route("tasks/{taskId}/delete")]
        [HttpPost]
        public IHttpActionResult DeleteTaskById(int taskId)
        {
            try
            {
                service.DeleteTaskById(taskId);
                return Ok();
            }
            catch
            {
                return InternalServerError();
            }
        }
    }
}
