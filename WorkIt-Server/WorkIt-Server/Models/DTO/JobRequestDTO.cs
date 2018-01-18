using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.DTO
{
    public class JobRequestDTO
    {
        public int JobId { get; set; }
        public int UserId { get; set; } 
        public string Description { get; set; }
    }
}