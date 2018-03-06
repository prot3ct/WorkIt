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

        [Route("requests/{requestId}")]
        [HttpGet]
        public IHttpActionResult GetTaskRequestById(int requestId)
        {
            try
            {
                return Ok(service.GetTaskRequestById(requestId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("tasks/{taskId}/requests")]
        [HttpGet]
        public IHttpActionResult GetRequestsForTask(int taskId)
        {
            try
            {
                return Ok(service.GetRequestsForTask(taskId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("requests")]
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

        [Route("requests/{requestId}/comments")]
        [HttpPost]
        public IHttpActionResult CreateTaskRequestComment(CommentDTO comment)
        {
            try
            {
                service.CreateTaskRequestComment(comment);
                return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }   

        [Route("requests/{requestId}/comments")]
        [HttpGet]
        public IHttpActionResult GetCommentsByTaskRequestId(int requestId)
        {
            try
            {
                return Ok(service.GetCommentsByTaskRequestId(requestId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }



        // TO DO
        [Route("requests/{requestId}/update")]
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
