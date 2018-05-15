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
        [HttpPost]
        public IHttpActionResult CreateTask(CreateTaskDTO jobInformation)
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

        [Route("tasks/{taskId}/update")]
        [HttpPost]
        public IHttpActionResult UpdateTask(EditTaskDTO task)
        {
            try
            {
                service.UpdateTask(task);
                return Ok();
            }
            catch (Exception e)
            {
                return InternalServerError(e);
            }
        }

        [Route("tasks/{taskId}")]
        [HttpGet]
        public IHttpActionResult GetTaskDetails(int taskId)
        {
            try
            {
                return Ok(service.GetTaskDetails(taskId));
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

        [Route("users/{userId}/available-tasks")]
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

        [Route("users/{userId}/my-tasks")]
        [HttpGet]
        public IHttpActionResult GetMyTasks(int userId)
        {
            try
            {
                return Ok(service.GetMyTasks(userId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("users/{userId}/assigned-tasks")]
        [HttpGet]
        public IHttpActionResult GetAssignedTasks(int userId)
        {
            try
            {
                return Ok(service.GetAssignedTasks(userId));
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
            catch (Exception e)
            {
                return InternalServerError(e);
            }
        }
    }
}
