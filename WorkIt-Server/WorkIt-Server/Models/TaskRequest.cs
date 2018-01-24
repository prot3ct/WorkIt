using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models
{
    public class TaskRequest
    {
        [Key]
        public int TaskRequestId { get; set; }

        [Required]
        public string Description { get; set; }

        [Required]
        [ForeignKey("User")]
        public int UserId { get; set; }
        public virtual User User { get; set; }

        [Required]
        [ForeignKey("Task")]
        public int TaskId { get; set; }
        public virtual Task Task { get; set; }
    }
}