using System;
using System.Web.Http;
using WorkIt_Server.BLL;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.Controllers
{
    [RoutePrefix("api")]
    public class TaskRequestsController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("tasks/{taskId}/requests")]
        [HttpGet]
        public IHttpActionResult GetRequestsForCurrentTask(int taskId)
        {
            try
            {
                return Ok(service.GetRequestsForCurrentTask(taskId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("tasks/requests/create")]
        [HttpPost]
        public IHttpActionResult CreateTaskRequest(TaskRequestDTO taskRequest)
        {
            try
            {
                service.CreateTaskRequest(taskRequest);
                return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("tasks/requests/{requestId}/delete")]
        [HttpPost]
        public IHttpActionResult DeleteTaskRequestById(int requestId)
        {
            try
            {
                service.DeleteTaskRequestById(requestId);
                return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
