using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.DTO
{
    public class CommentDTO
    {
        public int AuthorId { get; set; }
        public int TaskId { get; set; }
        public string Message { get; set; }
    }
}