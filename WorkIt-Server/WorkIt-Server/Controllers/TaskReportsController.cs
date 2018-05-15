using System;
using System.Web.Http;
using WorkIt_Server.BLL;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.Controllers
{
    [RoutePrefix("api")]
    public class TaskReportsController : ApiController
    {
        private BaseService service = new BaseService();

        //[Route("tasks/reports/create")]
        //[HttpPost]
        //public IHttpActionResult CreateTaskReport(TaskReportDTO taskReport)
        //{
        //    try
        //    {
        //        service.CreateTaskReport(taskReport);
        //        return Ok();
        //    }
        //    catch (Exception)
        //    {
        //        return InternalServerError();
        //    }
        //}
    }
}
