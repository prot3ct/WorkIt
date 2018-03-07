using System;
using System.Web.Http;
using WorkIt_Server.BLL;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.Controllers
{
    public class RaitingsController : ApiController
    {
        private BaseService service = new BaseService();

        [Route("users/{userId}/raitings")]
        [HttpGet]
        public IHttpActionResult GetRaitingByUserId(int userId)
        {
            try
            {
                return Ok(service.GetAllRaitingsByUserId(userId));
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }

        [Route("users/{userId}/raitings")]
        [HttpPost]
        public IHttpActionResult CreateRaiting(RaitingDTO raiting)
        {
            try
            {
                service.CreateRaiting(raiting);
                return Ok();
            }
            catch (Exception)
            {
                return InternalServerError();
            }
        }
    }
}
