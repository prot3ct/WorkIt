using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models
{
    public class TaskComments
    {
        [Key]
        public int TaskCommentsId { get; set; }

        [Required]
        [ForeignKey("Task")]
        public int TaskId { get; set; }
        public virtual Task Task { get; set; }

        [Required]
        [ForeignKey("Comment")]
        public int CommentId { get; set; }
        public virtual Comment Comment { get; set; }
    }
}