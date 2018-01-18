using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models
{
    public class JobRequests
    {
        [Key]
        public int JobRequestsId { get; set; }

        [Required]
        public string Description { get; set; }

        [Required]
        [ForeignKey("User")]
        public int UserId { get; set; }
        public virtual User User { get; set; }

        [Required]
        [ForeignKey("Job")]
        public int JobId { get; set; }
        public virtual Job Job { get; set; }
    }
}