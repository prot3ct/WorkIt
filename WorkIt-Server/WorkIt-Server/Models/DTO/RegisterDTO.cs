using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WorkIt_Server.Models.DTO
{
    public class RegisterDTO
    {
        public string Email { get; set; }
        public string PassHash { get; set; }
        public string Firstname { get; set; }
        public string Lastname { get; set; }
    }
}