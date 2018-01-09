using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models
{
    public class Job
    {
        [Key]
        public int JobId { get; set; }
        public string Title { get; set; }
        public DateTime StarDate { get; set; }
        public DateTime EndDate { get; set; }
        public string Description { get; set; }
        public string Reward { get; set; }
        public int minRaiting { get; set; }
        public int minJobsCompleted { get; set; }
        public bool isComplted { get; set; }


        public int CreatorId { get; set; }
        public User Creator { get; set; }
    }
}