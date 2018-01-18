using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.DTO
{
    public class UserReportDTO
    {
        public string Description { get; set; }
        public int AuthorUserId { get; set; }
        public int TargetUserId { get; set; }
    }
}