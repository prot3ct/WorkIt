using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models
{
    public class UserReport
    {
        [Key]
        public int UserReportId { get; set; }

        [Required]
        public string Descriptin { get; set; }

        [Required]
        [ForeignKey("User")]
        public int UserId { get; set; }
        public virtual User User { get; set; }
    }
}