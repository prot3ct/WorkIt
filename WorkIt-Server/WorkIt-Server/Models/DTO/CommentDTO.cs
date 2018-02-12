using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.DTO
{
    public class CommentDTO
    {
        public int CommentId { get; set; }
        public int TargetId { get; set; }
        public int UserId { get; set; }
        public string Body { get; set; }
    }
}