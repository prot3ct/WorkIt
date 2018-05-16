using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
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
        public string FullName { get; set; }

        public string Phone { get; set; }

        public byte[] Picture { get; set; }

        public double RaitingAsTasker { get; set; }

        public double RaitingAsSupervisor { get; set; }

        public int ReviewsAsTasker { get; set; }

        public int ReviewsAsSupervisor { get; set; }
    }
}