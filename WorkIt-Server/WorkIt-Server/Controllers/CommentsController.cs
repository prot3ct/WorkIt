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
    public class CommentsController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("jobs/{id}/comments")]
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

        [Route("jobs/{jobId}/comments/delete/{commentId}")]
        [HttpGet]
        public IHttpActionResult GetCommectsForJobById(int jobId, int commentId)
        {
            try
            {
                return Ok(service.DeleteCommentById(jobId, commentId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
