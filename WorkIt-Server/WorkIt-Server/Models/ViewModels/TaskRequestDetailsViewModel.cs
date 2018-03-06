using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.ViewModels
{
    public class TaskRequestDetailsViewModel
    {
        public string Description { get; set; }
        public int TaskRequestId { get; set; }
        public string TaskTitle { get; set; }
        public string Status { get; set; }
        public string Name { get; set; }
    }
}