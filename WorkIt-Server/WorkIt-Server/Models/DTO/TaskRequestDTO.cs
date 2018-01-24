using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.DTO
{
    public class TaskRequestDTO
    {
        public int TaskId { get; set; }
        public int UserId { get; set; } 
        public string Description { get; set; }
    }
}