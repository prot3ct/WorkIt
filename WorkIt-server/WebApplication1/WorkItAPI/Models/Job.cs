using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WorkItAPI.Models
{
    public class Job
    {
        [Key]
        public int JobID { get; set; }
        public string Title { get; set; }

        public User Creator { get; set; }
    }
}