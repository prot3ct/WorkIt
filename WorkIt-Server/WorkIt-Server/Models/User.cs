using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models
{
    public class User
    {
        [Key]
        public int UserId { get; set; }

        [Required]
        public string Email { get; set; }

        [Required]
        public string PassHash { get; set; }

        [Required]
        public string Firstname { get; set; }

        [Required]
        public string Lastname { get; set; }

        public double RaitingAsEmployee { get; set; }

        public double RaitingAsCreator { get; set; }

        public int TaskCompleted { get; set; }
    }
}