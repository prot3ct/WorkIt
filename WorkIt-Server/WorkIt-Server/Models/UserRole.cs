using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models
{
    public class UserRole
    {
        [Key]
        public int UserRoleId { get; set; }

        [Required]
        public string Name { get; set; }
    }
}