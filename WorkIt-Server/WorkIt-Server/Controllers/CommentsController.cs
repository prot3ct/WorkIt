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
    public class CommentsController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("tasks/{taskId}/comments")]
        [HttpGet]
        public IHttpActionResult CreateComment(CommentDTO comment)
        {
            try
            {
                service.CreateComment(comment);
                return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("tasks/{taskId}/comments")]
        [HttpGet]
        public IHttpActionResult GetCommentsByTaskId(int taskId)
        {
            try
            {
                return Ok(service.GetCommentsByTaskId(taskId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("tasks/comments/{commentId}/delete")]
        [HttpPost]
        public IHttpActionResult DeleteCommentById(int commentId)
        {
            try
            {
                service.DeleteCommentById(commentId);
                return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("tasks/{taskId}/comments/delete")]
        [HttpPost]
        public IHttpActionResult DeleteCommentByTaskId(int taskId)
        {
            try
            {
                service.DeleteCommentsByTaskId(taskId);
                return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
