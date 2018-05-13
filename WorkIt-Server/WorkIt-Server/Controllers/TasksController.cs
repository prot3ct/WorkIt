using System;
using System.Web.Http;
using WorkIt_Server.BLL;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.Controllers
{
    [RoutePrefix("api")]
    public class TasksController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("tasks")]
        [HttpGet]
        public IHttpActionResult GetAllAvailableTasks()
        {
            try
            {
                return Ok(service.GetAllAvailableTasks());
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("users/{userId}/tasks")]
        [HttpGet]
        public IHttpActionResult GetTasksByUser(int userId)
        {
            try
            {
                return Ok(service.GetTasksByUser(userId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("users/{userId}/completed-tasks")]
        [HttpGet]
        public IHttpActionResult GetCompletedTasksByUser(int userId)
        {
            try
            {
                return Ok(service.GetCompletedTasksByUser(userId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("tasks")]
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
