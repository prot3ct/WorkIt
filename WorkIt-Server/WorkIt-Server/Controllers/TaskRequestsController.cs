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

        [Route("users/{userId}/requests")]
        [HttpGet]
        public IHttpActionResult GetRequestsForCurrentUser(int userId)
        {
            try
            {
                return Ok(service.GetRequestsForCurrentUser(userId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("tasks/{taskId}/requests")]
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
