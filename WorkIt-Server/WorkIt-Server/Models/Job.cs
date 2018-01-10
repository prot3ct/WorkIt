using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using WorkIt_Server.Models.DTO;

namespace WorkIt_Server.Models
{
    public class Job
    {
        [Key]
        public int JobId { get; set; }

        [Required]
        public string Title { get; set; }

        [Required]
        public DateTime StartDate { get; set; }

        [Required]
        public DateTime EndDate { get; set; }

        [Required]
        public string Description { get; set; }

        [Required]
        public string Reward { get; set; }

        [Required]
        public int MinRaiting { get; set; }

        [Required]
        public int MinJobsCompleted { get; set; }

        public bool IsCompleted { get; set; }


        [Required]
        public virtual Place Place { get; set; }

        [Required]
        public virtual User Creator { get; set; }
    }
}