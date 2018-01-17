using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;
using System.Web;

namespace WorkIt_Server.Models
{
    public class TaskReports
    {
        [Key]
        public int TaskReportId { get; set; }

        [Required]
        public string Descriptin { get; set; }

        [Required]
        [ForeignKey("Task")]
        public int TaskId { get; set; }
        public virtual Task Task { get; set; }
    }
}