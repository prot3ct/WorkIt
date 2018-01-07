using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WorkItAPI.Models
{
    public class User
    {
        [Key]
        public int UserId { get; set; }
        public string Email { get; set; }
        public string PassHash { get; set; }
        public string Firstname { get; set; }
        public string Lastname { get; set; }
        public double RaitingAsEmployee { get; set; }
        public double RaitingAsCreator { get; set; }
        public int JobsCompleted { get; set; }
    }
}